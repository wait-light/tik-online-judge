package top.adxd.tikonlinejudge.executor.service.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.entity.Submit;

/**
 * @author light
 */
@Service
public class SubmitSender {
    public static final String SUBMIT_QUERY = "submit.direct";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(Submit submit){
        System.out.println("");
        rabbitTemplate.convertAndSend(SubmitSender.SUBMIT_QUERY,submit);
    }
}
