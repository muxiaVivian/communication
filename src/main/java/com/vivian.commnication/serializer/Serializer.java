package com.vivian.commnication.serializer;

public interface Serializer<T> {
    byte[] serialize(T message);
    T deserialize(byte[] message, Class<T> clazz);
}
