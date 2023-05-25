package balmasov.kafka.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    public static final String TOPIC = "Copra_topic";
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = TOPIC)
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        logger.info("Received message: {}", consumerRecord.toString());
    }
}