package com.vivian.commnication.config;

import com.vivian.commnication.enums.Protocol;
import com.vivian.commnication.enums.Topic;
import com.vivian.commnication.enums.TransportType;

import java.util.Map;

public class Config {
    private Topic topic;
    private String publisherHost;
    private String publisherPort;
    private Protocol protocol;
    private TransportType transportType;
    private Map<String, String> TransportParameter;

    public Topic getTopic() {
        return topic;
    }

    public String getPublisherHost() {
        return publisherHost;
    }

    public String getPublisherPort() {
        return publisherPort;
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
}
