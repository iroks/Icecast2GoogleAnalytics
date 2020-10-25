/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Set;

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class SourceStreamElement {

    //Number of listners that listen this mount point as a number
    @XmlElement(name="Listeners")
    int numListeners;

    //List of the Listner elements
    @XmlElement (name="listener")
    Set<SingleListenerElement> listeners;

    @XmlAttribute(name="mount")
    String mountPoint;

    public int getNumListeners() {
        return numListeners;
    }

    public void setNumListeners(int numListeners) {
        this.numListeners = numListeners;
    }

    public Set<SingleListenerElement> getListeners() {
        return listeners;
    }

    public void setListeners(Set<SingleListenerElement> listeners) {
        this.listeners = listeners;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }
}
