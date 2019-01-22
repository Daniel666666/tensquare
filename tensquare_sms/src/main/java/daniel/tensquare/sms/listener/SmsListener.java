package daniel.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void sendSms(Map map){
        System.out.println("接收到消息--"+map.get("mobile")+"--"+map.get("registCheckCode"));
    }
}
