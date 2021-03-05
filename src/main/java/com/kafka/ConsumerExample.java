package com.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerExample {

    private final Log logger = LogFactory.getLog(ConsumerExample.class);

    @KafkaListener(topics = "kafka-learn-sample")
    public void consume(final @Payload String message,
        final Acknowledgment acknowledgment) {

        logger.info(String.format("#### -> Consumed message -> ", message));
        acknowledgment.acknowledge();

    }

}