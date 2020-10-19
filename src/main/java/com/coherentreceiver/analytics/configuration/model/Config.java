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
package com.coherentreceiver.analytics.configuration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Basis configuration binding class
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {


    //how often the data will be transferred to the google analytics; value in sec
    @JsonProperty(value = "analytics-update-frequency")
    int analyticsUpdateFrequency;

    //how often the icecast server will be polled, value in sec
    @JsonProperty (value="icecast-update-frequency")
    int icecastUpdateFrequency;

    //how often the icecast server will be polled, value in sec
    @JsonProperty (value="influxdb-update-frequency")
    int influxDBUpdateFrequency;

    //Server element for configuration
    @JacksonXmlProperty(localName = "servers")
    @JacksonXmlElementWrapper(useWrapping = true)
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

    public int getInfluxDBUpdateFrequency() {
        return influxDBUpdateFrequency;
    }

    public void setInfluxDBUpdateFrequency(int influxDBUpdateFrequency) {
        this.influxDBUpdateFrequency = influxDBUpdateFrequency;
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
                "analyticsUpdateFrequency=" + analyticsUpdateFrequency +
                ", icecastUpdateFrequency=" + icecastUpdateFrequency +
                ", servers=" + servers +
                '}';
    }
}


