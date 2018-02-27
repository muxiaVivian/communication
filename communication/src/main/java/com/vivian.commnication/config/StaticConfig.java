package com.vivian.commnication.config;

import com.vivian.commnication.enums.Protocol;
import com.vivian.commnication.enums.TransportType;
import com.vivian.commnication.serializer.Serializer;

public class StaticConfig {
    public static final String SUBSCRIBE_TRANSPORTER = "SubscribeTransporter";
    public static final String PUBLISH_TRANSPORTER = "PublishTransporter";
    public static final String SERIALIZER = "Serializer";


    public static String getSubscribeTransportorClassName(TransportType transportType) {
        return transportType.toString() + SUBSCRIBE_TRANSPORTER;
    }

    public static String getPublishTransportorClassName(TransportType transportType) {
        return transportType.toString() + PUBLISH_TRANSPORTER;
    }

    public static String getSerializerClassName(Protocol protocol) {
        return protocol.toString() + SERIALIZER;
    }
}
