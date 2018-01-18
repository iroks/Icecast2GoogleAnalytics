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
import com.coherentreceiver.analytics.configuration.Server;
import com.coherentreceiver.analytics.fetcher.ListenersFetcher;
import com.coherentreceiver.analytics.helper.idgenerator.IDGenerator;
import com.coherentreceiver.analytics.icecastmodel.listclients.Listeners;
import com.coherentreceiver.analytics.icecastmodel.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.icecastmodel.stats.StreamProperty;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class GAUpdateTask extends AbstractTask<StreamProperty>  implements Runnable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GAUpdateTask.class);

    public void run() {

        //asynchronous multithreaded version
        logger.debug ("GAUpdate task Begin; transfer listeners to the GAServer");

    }

    public void updateSynchronous (StreamProperty streamProperty){

        Listeners listeners = getListeners (streamProperty);

        //there are no listeners anymore
        if (listeners==null) {
            return;

        }

        for (SingleListenerElement singleListener : listeners.getSource().getListeners()){
            makePageHit (streamProperty, singleListener);
        }

    }


    public Listeners getListeners (StreamProperty streamProperty) {

        Listeners listeners=null;

        try {

            Server server = streamProperty.getServer();

            ListenersFetcher listenersFetcher = new ListenersFetcher();
            listeners = listenersFetcher.getListeners(server.getListenerURL() + streamProperty.getMountPoint(), server.getLogin(), server.getPassword());


            if (listeners.getSource().getListeners() == null) {

                return null; //there are no listeners on this mount point

            }
        }catch (Exception e) {
            logger.error(e.toString());
        }

        return listeners;


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
            ga.post(pageViewHit);

        } catch (Exception e) {
            logger.error(e.toString());
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
