package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.configuration.model.Config;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.ServerService;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamPropertyService;
import com.coherentreceiver.analytics.ga.service.GAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public abstract class AbstractTask {

    final Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    @Autowired
    protected Config config;
    @Autowired
    protected StreamPropertyService streamPropertyService;
    @Autowired
    protected ServerService serverService;

    @Value ("#{appconfiguration.icecastUpdateFrequency}")
    protected long icecastUpdateFrequency;

    protected Listeners listeners;
    protected StreamProperty streamProperty;
    protected long prevTimeStampSeconds = 0;

    public void updateTask() {
        logger.info("start update process");

        Instant instant = Instant.now();
        long currTimeStampSeconds = instant.getEpochSecond();
        if ( (currTimeStampSeconds - prevTimeStampSeconds) > icecastUpdateFrequency) {
            logger.info ("processing by triggering Icecast request");
            config.getServers()
                    .stream()
                    .map(server -> serverService.getStreamPropertiesByServer(server))
                    .forEach(streamProperty -> streamProperty.forEach(sp -> {
                        streamPropertyService.setStreamProperty(sp);
                        Listeners listeners = streamPropertyService.getListeners();
                        //there are no listeners anymore
                        if (listeners == null) {
                            return;
                        }
                        this.listeners = listeners;
                        this.streamProperty = sp;
                        this.prevTimeStampSeconds=currTimeStampSeconds;
                        updateSynchronous(sp, listeners);
                    }));

        } else {
            logger.info ("processing using Icecast cache");
            updateSynchronous(this.streamProperty, this.listeners);
        }
        logger.info("finish influxdb process");
    }

    public abstract void updateSynchronous(StreamProperty sp, Listeners listeners);
}
