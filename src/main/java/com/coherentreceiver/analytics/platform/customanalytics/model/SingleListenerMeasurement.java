package com.coherentreceiver.analytics.platform.customanalytics.model;

import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.listclients.SingleListenerElement;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Measurement(name = "icecast_singlelistener", database = "icecast", timeUnit = TimeUnit.SECONDS)
public class SingleListenerMeasurement extends SingleListenerElement {


    @TimeColumn(timeUnit = java.util.concurrent.TimeUnit.MILLISECONDS)
    private Instant time;

    @Column (name = "title", tag = true)
    private String title;

    @Column (name = "mountpoint", tag = true)
    private String mountpoint;

    @Column (name = "country", tag = true)
        private String country;

    @Column (name = "city", tag = true)
    private String city;

    @Column (name = "serverhost", tag = true)
    private String serverHost;

    @Column (name = "gaccount", tag = true)
    private String gaAccount;

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

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getGaAccount() {
        return gaAccount;
    }

    public void setGaAccount(String gaAccount) {
        this.gaAccount = gaAccount;
    }
}
