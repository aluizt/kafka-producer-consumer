package br.com.kafka.service.consumer;

import br.com.kafka.config.KafkaProperties;
import br.com.kafka.util.UtilLogs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
@Slf4j
@AllArgsConstructor
public class ConsumerService {

    private KafkaProperties kafkaProperties;

    public void subscrible(String topic) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties())) {
            consumer.subscribe(Collections.singletonList(topic));
            consumer.poll(Duration.ofMillis(1000)).forEach(UtilLogs::showLogConsumer);
        } catch (KafkaException e) {
            log.error(e.getMessage());
        }
    }

    private Properties consumerProperties() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumerGroup());
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

}
