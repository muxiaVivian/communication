package com.vivian.commnication.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonSerializer<T> implements Serializer<T> {
    private static Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public byte[] serialize(T object) {
        try {
            String serialized = objectMapper.writeValueAsString(object);
            return serialized.getBytes();
        } catch (Exception e) {
            LOGGER.error("Error occurs when serializing object {}", object, e);
        }
        return null;
    }

    @Override
    public T deserialize(byte[] message, Class<T> clazz) {
        try {
            T obj = objectMapper.readValue(message, clazz);
            return obj;
        } catch (Exception e) {
            LOGGER.error("Error occurs when deserializing object {}", message, e);
        }
        return null;
    }
}
