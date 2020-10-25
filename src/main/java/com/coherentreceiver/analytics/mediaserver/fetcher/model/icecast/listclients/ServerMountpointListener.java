/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients;

/**
 *
 */
public class ServerMountpointListener {

    private int serverID;
    private String mountpoint;
    private Listeners listenersPrevious;
    private Listeners listenersCurrent;

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public String getMountpoint() {
        return mountpoint;
    }

    public void setMountpoint(String mountpoint) {
        this.mountpoint = mountpoint;
    }

    public Listeners getListenersPrevious() {
        return listenersPrevious;
    }

    public void setListenersPrevious(Listeners listenersPrevious) {
        this.listenersPrevious = listenersPrevious;
    }

    public Listeners getListenersCurrent() {
        return listenersCurrent;
    }

    public void setListenersCurrent(Listeners listenersCurrent) {
        this.listenersCurrent = listenersCurrent;
    }
}
