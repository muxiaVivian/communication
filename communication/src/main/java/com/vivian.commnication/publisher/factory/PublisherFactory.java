package com.vivian.commnication.publisher.factory;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.publisher.SimplePublisher;

public interface PublisherFactory {
    SimplePublisher generatePublisher(Config config);
}
