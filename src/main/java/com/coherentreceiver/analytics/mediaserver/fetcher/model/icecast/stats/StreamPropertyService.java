/**
 * Copyright 2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats;


import com.coherentreceiver.analytics.appconfiguration.model.Server;
import com.coherentreceiver.analytics.mediaserver.fetcher.service.ListenersFetcher;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamPropertyService {

    final Logger logger = LoggerFactory.getLogger(StreamPropertyService.class);

    @Autowired
    ListenersFetcher listenersFetcher;

    protected StreamProperty streamProperty;

    public StreamPropertyService (){
    }

    public StreamProperty getStreamProperty() {
        return streamProperty;
    }

    public void setStreamProperty(StreamProperty streamProperty) {
        this.streamProperty = streamProperty;
    }

    // @Cacheable
    public Listeners getListeners () {

        Listeners listeners=null;

        try {

            Server server = streamProperty.getServer();
            listeners = listenersFetcher.getListeners(server.getListenerURL() + streamProperty.getMountPoint(), server.getLogin(), server.getPassword());
            if (listeners.getSource().getListeners() == null) {

                return null; //there are no listeners on this mount point

            }
        }catch (Exception e) {
            logger.error(e.toString(), e);
        }

        return listeners;


    }

    public ListenersFetcher getListenersFetcher() {
        return listenersFetcher;
    }

    public void setListenersFetcher(ListenersFetcher listenersFetcher) {
        this.listenersFetcher = listenersFetcher;
    }
}
