/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.service;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 */
@Component
public class ListenersFetcher {

    public Listeners getListeners(String url, String login, String password){

        Listeners listeners=null;

        try{
            SecureGetFetcher fetcher = new SecureGetFetcher();
            JAXBContext jaxbContext = JAXBContext.newInstance(Listeners.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            listeners = (Listeners) jaxbUnmarshaller.unmarshal(fetcher.fetch(url, login, password));
        }catch (Exception e){System.out.println (e);}

        return listeners;

    }
}


