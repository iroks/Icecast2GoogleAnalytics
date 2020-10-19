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

package com.coherentreceiver.analytics.fetcher.service;

import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
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


