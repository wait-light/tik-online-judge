package top.adxd.tikonlinejudge.message.service.mq;/*
 * @author  wait-light
 * @date  2021/10/28 下午1:30
 */

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.message.api.Email;
import top.adxd.tikonlinejudge.message.service.impl.EmailSender;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@EnableRabbit
public class EmailConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @Autowired
    private EmailSender emailSender;

    @Bean
    public Queue emailQueue() {
        Queue queue = new Queue(Email.QUEUE, false, false, true);
        return queue;
    }

    @RabbitListener(queues = Email.QUEUE)
    public void submitConsumer(Email email, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
        try {
            emailSender.send(email.to,email.subject,email.content);
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            try {
                channel.basicNack(deliveryTag,false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
