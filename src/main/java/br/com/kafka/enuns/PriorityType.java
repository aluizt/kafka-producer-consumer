package br.com.kafka.enuns;

public enum PriorityType {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private String type;

    PriorityType(String type) {
        this.type = type;
    }

    public String getPriorityType() {
        return this.type;
    }

}
