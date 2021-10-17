package top.adxd.tikonlinejudge.executor.service.mq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
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
import top.adxd.tikonlinejudge.executor.service.impl.DockerJavaCodeJudge;

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
    @Autowired
    private List<DockerJavaCodeJudge> dockerJavaCodeJudges;
    @Autowired
    private IJudgeResultService judgeResultService;
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
        int index = javaJudgeIndex.getAndIncrement();
        List<JudgeResult> judge = dockerJavaCodeJudges.get(index % dockerJavaCodeJudges.size()).judge(submit);
        judgeResultService.updateCommitAfterJudge(judge,submit);
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }
}
