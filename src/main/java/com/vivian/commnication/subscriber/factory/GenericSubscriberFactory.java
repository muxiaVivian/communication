package com.vivian.commnication.subscriber.factory;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.serializer.JsonSerializer;
import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.subscriber.SimpleSubscriber;
import com.vivian.commnication.transporter.SubscribeTransporter;
import com.vivian.commnication.transporter.aeron.AeronSubscribeTransporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericSubscriberFactory implements SubscriberFactory {

    @Autowired
    private JsonSerializer jsonSerializer;

    @Override
    public SimpleSubscriber generateSubscriber(Config config) {
        SubscribeTransporter subscribeTransporter = null;
        Serializer serializer = null;
        //TODO: add default transporter, serializer and log
        switch (config.getTransportType()) {
            case AERON:
                subscribeTransporter = new AeronSubscribeTransporter(config.getTransportParameter());
        }
        switch (config.getProtocol()) {
            case JSON:
                serializer = jsonSerializer;
        }
        return new SimpleSubscriber(subscribeTransporter, serializer);
    }
}
