package com.vivian.commnication.config;

import com.vivian.commnication.enums.Protocol;
import com.vivian.commnication.enums.Topic;
import com.vivian.commnication.enums.TransportType;

import java.util.Map;

public class Config {
    private String configId;
    private Topic topic;
    private Protocol protocol;
    private TransportType transportType;
    private Map<String, String> TransportParameter;

    public Config(String configId, Topic topic, Protocol protocol, TransportType transportType, Map<String, String> transportParameter) {
        this.configId = configId;
        this.topic = topic;
        this.protocol = protocol;
        this.transportType = transportType;
        TransportParameter = transportParameter;
    }

    public Topic getTopic() {
        return topic;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Map<String, String> getTransportParameter() {
        return TransportParameter;
    }

    public String getConfigId() {
        return configId;
    }
}
