package br.com.kafka.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Slf4j
public class UtilLogs {

    public static boolean showLog(ConsumerRecord record) {
        log.info("Key:       " + record.key());
        log.info("Partition: " + record.partition());
        log.info("Value:     " + record.value());
        return true;
    }

}
