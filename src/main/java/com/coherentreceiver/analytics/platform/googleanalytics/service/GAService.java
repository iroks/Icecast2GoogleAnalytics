package com.coherentreceiver.analytics.platform.googleanalytics.service;

import com.brsanthu.googleanalytics.EventHit;
import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.PageViewHit;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.helper.idgenerator.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GAService {

    final Logger logger = LoggerFactory.getLogger(GAService.class);

    public GAService() {
    }

    public void makePageHit (StreamProperty streamProperty, SingleListenerElement singleListener) {


        try {
            if (streamProperty.getTitle() == null) streamProperty.setTitle("undefined");

            String undecodedTitle = streamProperty.getTitle();
            String decodedTitle = streamProperty.getCharacterDecoder().decode(undecodedTitle);

            PageViewHit pageViewHit = new PageViewHit(decodedTitle, streamProperty.getMountPoint() + "/" + decodedTitle);

            pageViewHit.userIp(singleListener.getIp());
            GoogleAnalytics ga = new GoogleAnalytics(streamProperty.getGaAccount());
            IDGenerator idGenerator = streamProperty.getIdGenerator();
            pageViewHit.clientId(idGenerator.getId(singleListener));
            if (singleListener.getReferer()!=null){
                pageViewHit.documentReferrer(singleListener.getReferer());
            }
            if (singleListener.getUserAgent()!=null){
                pageViewHit.userAgent(singleListener.getUserAgent());
            }

            ga.post(pageViewHit);


        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }

    public void makeEvent (StreamProperty streamProperty, SingleListenerElement listener, String eventCategory, String eventAction, String eventLabel, int eventValue){

        GoogleAnalytics ga = new GoogleAnalytics(streamProperty.getGaAccount());
        EventHit eventHit = new EventHit(eventCategory, eventAction, eventLabel, eventValue);

        IDGenerator idGenerator = streamProperty.getIdGenerator();

        eventHit.clientId(idGenerator.getId(listener));
        eventHit.userIp(listener.getIp());
        logger.debug (eventCategory + "- " +  eventAction +  " - " + eventLabel + " - " + eventValue + " for " + listener);
        ga.post(eventHit);

    }
}
