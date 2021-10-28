package top.adxd.tikonlinejudge.executor.service.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.common.config.SystemConfig;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import top.adxd.tikonlinejudge.executor.service.docker.judge.AbstractDockerJudgeTemplate;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCCodeJudge;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCppCodeJudge;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerJavaCodeJudge;
import top.adxd.tikonlinejudge.executor.single.Language;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author light
 */
@Component
@EnableRabbit
public class SubmitConsumer {


    private static final Logger logger = LoggerFactory.getLogger(SubmitConsumer.class);

    private final AtomicInteger javaJudgeIndex = new AtomicInteger(0);
    private final AtomicInteger cJudgeIndex = new AtomicInteger(0);
    private final AtomicInteger cppJudgeIndex = new AtomicInteger(0);
    @Autowired(required = false)
    private List<DockerJavaCodeJudge> dockerJavaCodeJudges;
    @Autowired(required = false)
    private List<DockerCCodeJudge> dockerCCodeJudges;
    @Autowired(required = false)
    private List<DockerCppCodeJudge> dockerCppCodeJudges;
    @Autowired
    private IJudgeResultService judgeResultService;


    @Bean
    public Queue submitQueue(){
        Queue queue = new Queue( SubmitSender.SUBMIT_QUERY,false,false,true);
        return queue;
    }

    @Bean("dynamicListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory dynamicListenerContainerFactory(@Autowired SystemConfig systemConfig,
                                                                                @Autowired ConnectionFactory connectionFactory){
        int coreSize = systemConfig.getCoreSize();
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(coreSize);
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(coreSize);
        simpleRabbitListenerContainerFactory.setConsumerBatchEnabled(false);
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return simpleRabbitListenerContainerFactory;
    }


    @RabbitListener(containerFactory = "dynamicListenerContainerFactory",queues = SubmitSender.SUBMIT_QUERY)
    public void submitConsumer(Submit submit, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
        Language languageType = submit.getLanguageType();
        List list = judgeList(languageType);
        if (list ==null || list.isEmpty()){
            try {
                channel.basicNack(deliveryTag,false,true);
                logger.info("unsupport language");
                return;
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                return;
            }
        }
        AtomicInteger atomicInteger = getAtomicInteger(languageType);
        int index = atomicInteger.getAndIncrement();
        List<JudgeResult> judgeResultList  = ((AbstractDockerJudgeTemplate) list.get(index % dockerJavaCodeJudges.size())).judge(submit);
        judgeResultService.updateCommitAfterJudge(judgeResultList,submit);
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private List judgeList(Language language){
        if (language == Language.JAVA){
            return dockerJavaCodeJudges;
        }else if (language == Language.CPP){
            return dockerCppCodeJudges;
        }else if (language == Language.C){
            return dockerCCodeJudges;
        }
        return null;
    }
    private AtomicInteger getAtomicInteger(Language language){
        if (language == Language.JAVA){
            return javaJudgeIndex;
        }else if (language == Language.CPP){
            return cppJudgeIndex;
        }else if (language == Language.C){
            return cJudgeIndex;
        }
        return null;
    }
}
