package br.com.kafka;

import br.com.kafka.model.PublisherModel;
import br.com.kafka.model.SendInputModel;
import br.com.kafka.service.consumer.ConsumerService;
import br.com.kafka.service.publischer.PublischerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class Teste {

    private final PublischerService publischerService;
    private final ConsumerService consumerService;

    @PostConstruct
    public void test() throws Exception {
        var producerModel = PublisherModel.builder().msg(SendInputModel.builder().messageId("teste").build()).topic("notification").build();
        publischerService.sendNotification(producerModel);
        consumerService.subscrible("notification");
    }

}
