/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.platform.streaminganalytics.model;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import javax.xml.bind.annotation.XmlElement;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Measurement(name = "icecast_singlelistener", database = "icecast", timeUnit = TimeUnit.SECONDS)
public class SingleListenerMeasurement {

    @TimeColumn(timeUnit = java.util.concurrent.TimeUnit.MILLISECONDS)
    private Instant time;

    //listener ip address
    @Column(name = "ip", tag = false)
    protected String ip;

    //listener user agent
    @Column (name = "useragent", tag = true)
    protected String userAgent ;

    //listener connection time
    @Column (name = "connected", tag = false)
    protected long connected;

    @XmlElement (name="Referer")
    protected String referer;

    //listener icecast id
    @Column (name = "id", tag = false)
    protected String id;



    @Column (name = "title", tag = true)
    private String title;

    @Column (name = "mountpoint", tag = true)
    private String mountpoint;

    @Column (name = "country", tag = true)
        private String country;

    @Column (name = "city", tag = true)
    private String city;

    @Column (name = "gaccount", tag = true)
    private String gaAccount;

    public SingleListenerMeasurement (){}

    public SingleListenerMeasurement (SingleListenerElement se){
        this.connected = Optional.ofNullable(se.getConnected()).orElse(0l);
        this.id = Optional.ofNullable(se.getId()).orElse("");
        this.referer = Optional.ofNullable(se.getReferer()).orElse("");
        this.userAgent = Optional.ofNullable(se.getUserAgent()).orElse("");
        this.ip = Optional.ofNullable(se.getIp()).orElse("0.0.0.0");
        this.time = Instant.now();

    }


    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public long getConnected() {
        return connected;
    }

    public void setConnected(long connected) {
        this.connected = connected;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMountpoint() {
        return mountpoint;
    }

    public void setMountpoint(String mountpoint) {
        this.mountpoint = mountpoint;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGaAccount() {
        return gaAccount;
    }

    public void setGaAccount(String gaAccount) {
        this.gaAccount = gaAccount;
    }
}
