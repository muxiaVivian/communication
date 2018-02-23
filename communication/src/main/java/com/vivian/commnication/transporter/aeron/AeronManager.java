package com.vivian.commnication.transporter.aeron;

import io.aeron.samples.SamplesUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import io.aeron.Aeron;
import io.aeron.driver.MediaDriver;

import static io.aeron.Aeron.connect;

@Component
public class AeronManager {
    private static final boolean EMBEDDED_MEDIA_DRIVER = true;
    private Aeron aeron;

    @PostConstruct
    public void setup() {
        final MediaDriver driver = EMBEDDED_MEDIA_DRIVER ? MediaDriver.launchEmbedded() : null;
        final Aeron.Context ctx =
                new Aeron.Context().availableImageHandler(SamplesUtil::printAvailableImage).unavailableImageHandler(SamplesUtil::printUnavailableImage);
        if (EMBEDDED_MEDIA_DRIVER)
        {
            ctx.aeronDirectoryName(driver.aeronDirectoryName());
        }
        aeron = connect(ctx);
    }

    public Aeron getAeron() {
        return aeron;
    }

}
