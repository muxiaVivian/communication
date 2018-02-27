package com.vivian.commnication.transporter;

public interface PublishTransporter extends Transporter{
    void publish(byte[] message);
}
