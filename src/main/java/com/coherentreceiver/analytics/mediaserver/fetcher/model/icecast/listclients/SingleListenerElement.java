/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients;


import org.influxdb.annotation.Column;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
// @Measurement(name = "icecast_singlelistener", database = "icecast", timeUnit = TimeUnit.SECONDS)
public class SingleListenerElement {

    //listener ip address
    @XmlElement (name="IP")
    protected String ip;

    //listener user agent
    @XmlElement (name="UserAgent")
    protected String userAgent ;

    //listener connection time
    @XmlElement (name="Connected")
    protected long connected;

    @XmlElement (name="Referer")
    protected String referer;

    //listener icecast id
    @XmlElement (name="ID")
    protected String id;

    private SingleListenerState listenerState = SingleListenerState.NOSTATE;

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


    public SingleListenerState getListenerState() {
        return listenerState;
    }

    public void setListenerState(SingleListenerState listenerState) {
        this.listenerState = listenerState;
    }

    public static SingleListenerElement getDefaultListner (){
        SingleListenerElement singleListenerElement = new SingleListenerElement();
        singleListenerElement.setId("0");
        singleListenerElement.setIp("0.0.0.0");
        singleListenerElement.setUserAgent("default");
        singleListenerElement.setConnected(0);
        return singleListenerElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleListenerElement that = (SingleListenerElement) o;

        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (userAgent != null ? !userAgent.equals(that.userAgent) : that.userAgent != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SingleListenerElement{" +
                "ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", connected=" + connected +
                ", id='" + id + '\'' +
                '}';
    }
}
