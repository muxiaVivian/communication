package com.vivian.commnication.subscriber.factory;

import com.vivian.commnication.config.Config;
import com.vivian.commnication.subscriber.SimpleSubscriber;

public interface SubscriberFactory {
    SimpleSubscriber generateSubscriber(Config config);
}
