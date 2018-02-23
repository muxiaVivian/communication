package com.vivian.commnication.publisher.factory;


import com.vivian.commnication.config.Config;
import com.vivian.commnication.publisher.SimplePublisher;
import com.vivian.commnication.serializer.JsonSerializer;
import com.vivian.commnication.serializer.Serializer;
import com.vivian.commnication.transporter.aeron.AeronPublishTransporter;
import com.vivian.commnication.transporter.PublishTransporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericPublisherFactory implements PublisherFactory {
    @Autowired
    private JsonSerializer jsonSerializer;

    @Override
    public SimplePublisher generatePublisher(Config config) {
        PublishTransporter publishTransporter = null;
        Serializer serializer = null;
        //TODO: add default transporter, serializer and log
        switch (config.getTransportType()) {
            case AERON:
                publishTransporter = new AeronPublishTransporter(config.getTransportParameter());
        }
        switch (config.getProtocol()) {
            case JSON:
                serializer = jsonSerializer;
        }
        return new SimplePublisher(publishTransporter, serializer);
    }
}
