package com.vivian.commnication.transporter;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.config.StaticConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TransporterFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TransporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public SubscribeTransporter generateSubscribeTransportor(Config config) {
        try {
            String transportorClassName = StaticConfig.getSubscribeTransportorClassName(config.getTransportType());
            Class transportorClass = Class.forName(transportorClassName);
            return (SubscribeTransporter)context.getBean(transportorClass, config.getTransportParameter());
        } catch (Exception e) {
            LOGGER.error("Failed to create transportor=> {}", e);
        }
        return null;
    }

    public PublishTransporter generatePublishTransportor(Config config) {
        try {
            String transportorClassName = StaticConfig.getPublishTransportorClassName(config.getTransportType());
            Class transportorClass = Class.forName(transportorClassName);
            return (PublishTransporter)context.getBean(transportorClass, config.getTransportParameter());
        } catch (Exception e) {
            LOGGER.error("Failed to create transportor=> {}", e);
        }
        return null;
    }
}
