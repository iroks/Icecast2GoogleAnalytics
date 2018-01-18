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

package com.coherentreceiver.analytics.ga;

import java.time.LocalDateTime;

/**
 * Created by igor on 26.04.17.
 */
public class GAObject {

    //google client id
    private String googleID;

    //server Address
    private String serverAddress;

    //mountPoint name
    private String mountPoint;

    //current title on the stream
    private String title;

    //GA-Account number for the client
    private String gaAccount;

    //ip address of the listener for google analytics
    private String ip;

    //userAgent
    private String userAgent;

    //listener connection time
    private long connected;

    //listener icecast id
    private String icecastid;

    private LocalDateTime timeStamp;


    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGaAccount() {
        return gaAccount;
    }

    public void setGaAccount(String gaAccount) {
        this.gaAccount = gaAccount;
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

    public String getIcecastid() {
        return icecastid;
    }

    public void setIcecastid(String icecastid) {
        this.icecastid = icecastid;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GAObject gaObject = (GAObject) o;

        if (googleID != null ? !googleID.equals(gaObject.googleID) : gaObject.googleID != null) return false;
        if (serverAddress != null ? !serverAddress.equals(gaObject.serverAddress) : gaObject.serverAddress != null)
            return false;
        if (mountPoint != null ? !mountPoint.equals(gaObject.mountPoint) : gaObject.mountPoint != null) return false;
        if (gaAccount != null ? !gaAccount.equals(gaObject.gaAccount) : gaObject.gaAccount != null) return false;
        if (ip != null ? !ip.equals(gaObject.ip) : gaObject.ip != null) return false;
        if (userAgent != null ? !userAgent.equals(gaObject.userAgent) : gaObject.userAgent != null) return false;
        return icecastid != null ? icecastid.equals(gaObject.icecastid) : gaObject.icecastid == null;

    }

    @Override
    public int hashCode() {
        int result = googleID != null ? googleID.hashCode() : 0;
        result = 31 * result + (serverAddress != null ? serverAddress.hashCode() : 0);
        result = 31 * result + (mountPoint != null ? mountPoint.hashCode() : 0);
        result = 31 * result + (gaAccount != null ? gaAccount.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (icecastid != null ? icecastid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GAObject{" +
                "googleID='" + googleID + '\'' +
                ", serverAddress='" + serverAddress + '\'' +
                ", mountPoint='" + mountPoint + '\'' +
                ", title='" + title + '\'' +
                ", gaAccount='" + gaAccount + '\'' +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", connected=" + connected +
                ", icecastid='" + icecastid + '\'' +
                '}';
    }
}
