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
package com.coherentreceiver.analytics.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * Basis configuration binding class
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class Configuration {

    //how often the data will be transferred to the google analytics; value in sec
    @XmlElement(name="analytics-update-frequency")
    int analyticsUpdateFrequency;

    //how often the icecast server will be polled, value in sec
    @XmlElement(name="icecast-update-frequency")
    int icecastUpdateFrequency;



    //Server element for configuration
        @XmlElement(name="server")
        List<Server> servers;

    public int getAnalyticsUpdateFrequency() {
        return analyticsUpdateFrequency;
    }

    public void setAnalyticsUpdateFrequency(int analyticsUpdateFrequency) {
        this.analyticsUpdateFrequency = analyticsUpdateFrequency;
    }

    public int getIcecastUpdateFrequency() {
        return icecastUpdateFrequency;
    }

    public void setIcecastUpdateFrequency(int icecastUpdateFrequency) {
        this.icecastUpdateFrequency = icecastUpdateFrequency;
    }

    public List<Server> getServers() {
        return servers;
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "servers=" + servers +
                '}';
    }
}


