package com.Niek125.messageservice.kafka;

public class KafkaHeader {
    private Action action;
    private String object;

    public KafkaHeader() {
    }

    public KafkaHeader(Action action, String object) {
        this.action = action;
        this.object = object;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
