package com.vivian.commnication.transport.aeron;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import io.aeron.Aeron;
import io.aeron.driver.MediaDriver;
import static io.aeron.samples.SampleConfiguration.EMBEDDED_MEDIA_DRIVER;

@Component
public class AeronManager {
    private Aeron aeron;

    @PostConstruct
    public void setup(){
        final MediaDriver driver = EMBEDDED_MEDIA_DRIVER ? MediaDriver.launchEmbedded() : null;
        final Aeron.Context ctx = new Aeron.Context();
        aeron = Aeron.connect(ctx);
    }

    public Aeron getAeron() {
        return aeron;
    }

}
