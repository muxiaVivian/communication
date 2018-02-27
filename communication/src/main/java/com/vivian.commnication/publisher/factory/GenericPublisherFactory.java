package com.vivian.commnication.publisher.factory;


import com.vivian.commnication.config.Config;
import com.vivian.commnication.publisher.SimplePublisher;
import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.serializer.SerializerFactory;
import com.vivian.commnication.transporter.TransporterFactory;
import com.vivian.commnication.transporter.PublishTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericPublisherFactory implements PublisherFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(GenericPublisherFactory.class);
    @Autowired
    private SerializerFactory serializerFactory;
    @Autowired
    private TransporterFactory transportorFactory;

    @Override
    public SimplePublisher generatePublisher(Config config) {
        PublishTransporter publishTransporter = transportorFactory.generatePublishTransportor(config);
        Serializer serializer  = serializerFactory.generateSerializer(config.getProtocol());
        return new SimplePublisher(publishTransporter, serializer);
    }

}
