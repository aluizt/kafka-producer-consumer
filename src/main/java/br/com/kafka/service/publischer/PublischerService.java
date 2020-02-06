package br.com.kafka.service.publischer;

import br.com.kafka.config.KafkaProperties;
import br.com.kafka.model.PublisherModel;
import br.com.kafka.util.UtilLogs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@Slf4j
@AllArgsConstructor
public class PublischerService {

    private KafkaProperties kafkaProperties;

    public void sendNotification(PublisherModel message) throws Exception {
        KafkaProducer<String, String> kafkaProducer = getKafkaProducer();
        kafkaProducer.send(getRecord(message.getTopic(), message), notificationCallback());
        kafkaProducer.close();
    }

    public Callback notificationCallback() {
        return (metadata, exception) -> {
            if (exception == null) {
                UtilLogs.showLogPublischer(metadata);
            } else {
                log.error("Eror while producing message", exception);
            }
        };
    }

    private Properties publischerProperties() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    private KafkaProducer<String, String> getKafkaProducer() {
        return new KafkaProducer<>(publischerProperties());
    }

    private ProducerRecord<String, String> getRecord(String topic, PublisherModel message) throws Exception {
        return new ProducerRecord<>(topic, new ObjectMapper().writeValueAsString(message));
    }
}
