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


package com.coherentreceiver.analytics.icecastmodel.listclients;

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
