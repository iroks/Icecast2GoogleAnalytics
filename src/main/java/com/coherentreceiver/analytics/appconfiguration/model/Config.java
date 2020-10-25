/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.appconfiguration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;


/**
 * Basis configuration binding class
 */

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

    //path to geodatabase
    @JsonProperty (value="geodatabase-path")
    String geoDatabasePath;

    //geolocation provider
    @JsonProperty (value="geolocation-provider")
    String geolocationProvder;


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

    public String getGeoDatabasePath() {
        return geoDatabasePath;
    }

    public void setGeoDatabasePath(String geoDatabasePath) {
        this.geoDatabasePath = geoDatabasePath;
    }

    public String getGeolocationProvder() {
        return geolocationProvder;
    }

    public void setGeolocationProvder(String geolocationProvder) {
        this.geolocationProvder = geolocationProvder;
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


