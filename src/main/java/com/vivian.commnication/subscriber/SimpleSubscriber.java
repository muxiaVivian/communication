package com.vivian.commnication.subscriber;

import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.transporter.SubscribeTransporter;

public class SimpleSubscriber implements Subscriber {
    private SubscribeTransporter transporter;
    private Serializer serializer;

    public SimpleSubscriber(SubscribeTransporter transporter, Serializer serializer) {
        this.transporter = transporter;
        this.serializer = serializer;
    }

    @Override
    public void subscribe() {
        transporter.subscribe();
    }
}
