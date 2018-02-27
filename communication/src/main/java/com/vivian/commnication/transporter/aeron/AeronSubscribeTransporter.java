package com.vivian.commnication.transporter.aeron;

import com.vivian.commnication.enums.AeronConfig;
import com.vivian.commnication.transporter.SubscribeTransporter;
import io.aeron.FragmentAssembler;
import io.aeron.Subscription;
import io.aeron.logbuffer.FragmentHandler;
import io.aeron.samples.SamplesUtil;
import org.agrona.concurrent.BackoffIdleStrategy;
import org.agrona.concurrent.IdleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.aeron.samples.SampleConfiguration.FRAGMENT_COUNT_LIMIT;

@Component
@Scope("prototype")
public class AeronSubscribeTransporter implements SubscribeTransporter {
    private final AtomicBoolean running = new AtomicBoolean(true);
    private int streamId;
    private String channel;
    private Subscription subscription;

    @Autowired
    private AeronManager aeronManager;

    public AeronSubscribeTransporter(Map<String, String> transportConfig){
        streamId = Integer.valueOf(transportConfig.get(AeronConfig.STREAM_ID.toString()));
        channel = transportConfig.get(AeronConfig.CHANNEL.toString());
        subscription = aeronManager.getAeron().addSubscription(channel, streamId);
    }

    @Override
    public void tearDown() {
        running.set(false);
    }

    @Override
    public void subscribe() {
        Thread thread = new Thread(() -> {
            FragmentHandler fragmentHandler = SamplesUtil.printStringMessage(streamId);
            final IdleStrategy idleStrategy = new BackoffIdleStrategy(
                    100, 10, TimeUnit.MICROSECONDS.toNanos(1), TimeUnit.MICROSECONDS.toNanos(100));
            //TODO: research on idleStrategy, fragmentHandler and ExecutiveThreadPool for subscription thread
            while (running.get()) {
                int fragmentsRead = subscription.poll(fragmentHandler, FRAGMENT_COUNT_LIMIT);
                idleStrategy.idle(fragmentsRead);
            }
        });
        thread.start();
    }
}
