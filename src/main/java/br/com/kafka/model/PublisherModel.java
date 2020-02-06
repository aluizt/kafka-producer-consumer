package br.com.kafka.model;

import br.com.kafka.enuns.ExchangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherModel {
    private SendInputModel msg;
    private String topic;
}

