/*     Copyright 2017 SWI Kommunikations- und Computer GmbH
*
*        Licensed under the Apache License, Version 2.0 (the "License");
*        you may not use this file except in compliance with the License.
*        You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*        Unless required by applicable law or agreed to in writing, software
*       distributed under the License is distributed on an "AS IS" BASIS,
*        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*        See the License for the specific language governing permissions and
*        limitations under the License.
*/


package com.coherentreceiver.analytics.task;

import com.brsanthu.googleanalytics.EventHit;
import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.PageViewHit;
import com.coherentreceiver.analytics.configuration.model.Config;
import com.coherentreceiver.analytics.configuration.service.Configurator;
import com.coherentreceiver.analytics.helper.idgenerator.IDGenerator;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.ServerService;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class GAUpdateTask   {

    final Logger logger = LoggerFactory.getLogger(GAUpdateTask.class);

    @Autowired
    private Config config;

    @Autowired
    private StreamPropertyService streamPropertyService;

    @Autowired
    private ServerService serverService;


  //  @Scheduled(fixedDelay = 40000)
    public void gaUpdateTask () {

        config.getServers()
                .stream()
                .map(server -> serverService.getStreamPropertiesByServer (server))
                .forEach(streamProperty -> streamProperty.forEach(sp -> updateSynchronous(sp)));

        logger.debug("ga update process done");

    }

     public void updateSynchronous (StreamProperty sp){

         streamPropertyService.setStreamProperty(sp);
         Listeners listeners = streamPropertyService.getListeners ();


        //there are no listeners anymore
        if (listeners==null) {
            return;

        }

        for (SingleListenerElement singleListener : listeners.getSource().getListeners()){
            makePageHit (sp, singleListener);
        }

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

    public StreamPropertyService getStreamPropertyService() {
        return streamPropertyService;
    }

    public void setStreamPropertyService(StreamPropertyService streamPropertyService) {
        this.streamPropertyService = streamPropertyService;
    }
}
