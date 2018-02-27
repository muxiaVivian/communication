package com.vivian.commnication.transporter.aeron;

import com.vivian.commnication.enums.AeronConfig;
import com.vivian.commnication.transporter.PublishTransporter;
import io.aeron.Publication;
import org.agrona.BufferUtil;
import org.agrona.concurrent.UnsafeBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class AeronPublishTransporter implements PublishTransporter {
    //TODO: research size of buffer
    private static final UnsafeBuffer BUFFER = new UnsafeBuffer(BufferUtil.allocateDirectAligned(256, 64));
    private int streamId;
    private String channel;
    private Publication publication;

    @Autowired
    private AeronManager aeronManager;

    public AeronPublishTransporter(Map<String, String> transportConfig){
        streamId = Integer.valueOf(transportConfig.get(AeronConfig.STREAM_ID.toString()));
        channel = transportConfig.get(AeronConfig.CHANNEL.toString());
        publication = aeronManager.getAeron().addPublication(channel, streamId);
    }

    @Override
    public void publish(byte[] message) {
        //TODO: confirm how to get byte of message
        BUFFER.putBytes(0, message);
        publication.offer(BUFFER, 0, message.length);

    }
}
