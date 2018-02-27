package com.vivian.client.config;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.enums.Protocol;
import com.vivian.commnication.enums.Topic;
import com.vivian.commnication.enums.TransportType;
import com.vivian.commnication.transporter.Transporter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.vivian.commnication.enums.AeronConfig.CHANNEL;
import static com.vivian.commnication.enums.AeronConfig.STREAM_ID;

@Component
public class ConfigService {
    public Config getConfigByTopicForPublisher(Topic topic){
        Map<String, String> transportorConfig = new HashMap<>();
        transportorConfig.put(STREAM_ID.toString(), "1");
        transportorConfig.put(CHANNEL.toString(), "aeron:udp?control=0.0.0.0:40121");
        return new Config("1", topic, Protocol.JSON, TransportType.AERON, transportorConfig);
    }

    public Config getConfigByTopicForSubscriber(Topic topic){
        Map<String, String> transportorConfig = new HashMap<>();
        transportorConfig.put(STREAM_ID.toString(), "1");
        transportorConfig.put(CHANNEL.toString(), "aeron:udp?endpoint=0.0.0.0:4050|control=localhost:40121");
        return new Config("1", topic, Protocol.JSON, TransportType.AERON, transportorConfig);
    }
}
