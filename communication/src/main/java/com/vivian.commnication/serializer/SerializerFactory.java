package com.vivian.commnication.serializer;

import com.vivian.commnication.config.StaticConfig;
import com.vivian.commnication.enums.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SerializerFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(SerializerFactory.class);

    @Autowired
    private ApplicationContext context;

    public Serializer generateSerializer(Protocol protocol) {
        try {
            String serializerClassName = StaticConfig.getSerializerClassName(protocol);
            Class serializerClass = Class.forName(serializerClassName);
            return (Serializer)context.getBean(serializerClass);
        } catch (Exception e) {
            LOGGER.error("Failed to create serializer=> {}", e);
        }
        return null;
    }
}
