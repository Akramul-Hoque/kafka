package practice.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    public KafkaTemplate<String, Object> template;

    public void SendMessageToTopics(String message) {
        CompletableFuture<SendResult<String, Object>> send = template.send("my_kafka_practice", message);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent Message=[" + message + " With offset=[" + result.getRecordMetadata().offset());
            } else {
                System.out.println("Unable to Sent Message=[" + message + "With offset=[" + ex.getMessage());

            }
        });
    }
}
