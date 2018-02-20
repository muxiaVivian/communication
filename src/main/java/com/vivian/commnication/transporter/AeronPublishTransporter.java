package com.vivian.commnication.transporter;

import com.vivian.commnication.enums.AeronConfig;

import java.util.Map;

public class AeronPublishTransporter implements IPublishTransporter {
    private int streamId;
    private String channel;

    public AeronPublishTransporter(Map<String, String> transportConfig){
        streamId = Integer.valueOf(transportConfig.get(AeronConfig.STREAM_ID.toString()));
        channel = transportConfig.get(AeronConfig.CHANNEL.toString());
    }

    @Override
    public void publish(Object object) {

    }
}
