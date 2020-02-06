package br.com.kafka.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class UtilLogs {

    public static boolean showLogConsumer(ConsumerRecord record) {
        log.info("Key:       " + record.key() + "\n" +
                 "Partition: " + record.partition() + "\n" +
                 "Value:     " + record.value());
        return true;
    }

    public static boolean showLogPublischer(RecordMetadata metadata){
        log.info("Topic:     " + metadata.topic() + "\n" +
                "Offset:    " + metadata.offset() + "\n" +
                "Partition: " + metadata.partition() + "\n" +
                "TimeStamp: " + metadata.timestamp());
        return true;
    }

}
