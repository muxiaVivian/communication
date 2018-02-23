package com.vivian.commnication.publisher;

import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.transporter.PublishTransporter;

public class SimplePublisher implements IPublisher{
    private PublishTransporter transporter;
    private Serializer serializer;

    public SimplePublisher(PublishTransporter transporter, Serializer serializer) {
        this.transporter = transporter;
        this.serializer = serializer;
    }

    @Override
    public void publish(Object message) {
        byte[] serializedMessage = serializer.serialize(message);
        transporter.publish(serializedMessage);
    }
}
