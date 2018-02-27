package com.vivian.commnication.subscriber.factory;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.serializer.SerializerFactory;
import com.vivian.commnication.subscriber.SimpleSubscriber;
import com.vivian.commnication.transporter.SubscribeTransporter;
import com.vivian.commnication.transporter.TransporterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GenericSubscriberFactory implements SubscriberFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(GenericSubscriberFactory.class);
    @Autowired
    private SerializerFactory serializerFactory;
    @Autowired
    private TransporterFactory transportorFactory;

    @Override
    public SimpleSubscriber generateSubscriber(Config config) {
        SubscribeTransporter subscribeTransporter = transportorFactory.generateSubscribeTransportor(config);
        Serializer serializer = serializerFactory.generateSerializer(config.getProtocol());
        return new SimpleSubscriber(subscribeTransporter, serializer);
    }

}
