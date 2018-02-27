package com.vivian.client.test;

import com.vivian.client.config.ConfigService;
import com.vivian.commnication.enums.Topic;
import com.vivian.commnication.publisher.Publisher;
import com.vivian.commnication.publisher.factory.GenericPublisherFactory;
import com.vivian.commnication.subscriber.Subscriber;
import com.vivian.commnication.subscriber.factory.GenericSubscriberFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommunicationTest {

    @Autowired
    private GenericPublisherFactory genericPublisherFactory;
    @Autowired
    private GenericSubscriberFactory genericSubscriberFactory;
    @Autowired
    private ConfigService configService;
    @EventListener(ApplicationReadyEvent.class)
    public void test(){
        System.out.println("test");
        Publisher publisher = genericPublisherFactory.generatePublisher(configService.getConfigByTopicForPublisher(Topic.TEST));
        Subscriber subscriber = genericSubscriberFactory.generateSubscriber(configService.getConfigByTopicForSubscriber(Topic.TEST));


    }

}