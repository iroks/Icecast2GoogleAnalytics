/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.platform.googleanalytics.service.GAService;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class GAUpdateTask extends AbstractTask   {

    final Logger logger = LoggerFactory.getLogger(GAUpdateTask.class);

    @Autowired
    private GAService gaService;

    // @Scheduled(fixedDelayString = "#{appconfiguration.analyticsUpdateFrequency*1000}")
    public void gaUpdateTask () {
        super.updateTask();
    }

     public void updateSynchronous (StreamProperty sp, Listeners listeners){

           for (SingleListenerElement singleListener : listeners.getSource().getListeners()){
            gaService.makePageHit (sp, singleListener);
        }

    }

    public StreamPropertyService getStreamPropertyService() {
        return streamPropertyService;
    }

    public void setStreamPropertyService(StreamPropertyService streamPropertyService) {
        this.streamPropertyService = streamPropertyService;
    }
}
