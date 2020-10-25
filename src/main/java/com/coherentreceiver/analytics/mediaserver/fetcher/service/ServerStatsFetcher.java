/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.mediaserver.fetcher.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.ServerStats;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ServerStatsFetcher {

    public ServerStats getServerStats(String url, String login, String password){

        ServerStats serverStats=null;

        try{
        SecureGetFetcher fetcher = new SecureGetFetcher();
        JAXBContext jaxbContext = JAXBContext.newInstance(ServerStats.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        serverStats = (ServerStats) jaxbUnmarshaller.unmarshal(fetcher.fetch(url, login, password));
        }catch (Exception e){System.out.println (e);}

        return serverStats;

    }
}
