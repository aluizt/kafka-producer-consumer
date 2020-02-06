package br.com.kafka.enuns;

public enum ExchangeType {

    DIRECT("direct"),
    TOPIC("topic"),
    FANOUT("fanout"),
    HEADER("header");

    private final String exchangeName;

    ExchangeType(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeName() {
        return this.exchangeName;
    }

}