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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class SingleListenerElement {

    //listener ip address
    @XmlElement (name="IP")
    private String ip;

    //listener user agent
    @XmlElement (name="UserAgent")
    private String userAgent ;

    //listener connection time
    @XmlElement (name="Connected")
    private long connected;

    @XmlElement (name="Referer")
    private String referer;

    //listener icecast id
    @XmlElement (name="ID")
    private String id;

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
