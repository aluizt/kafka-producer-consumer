package br.com.kafka.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class KafkaProperties {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.bootstrap.server}")
    private String bootstrapServer;

    @Value("${kafka.consumer.group}")
    private String consumerGroup;
}
