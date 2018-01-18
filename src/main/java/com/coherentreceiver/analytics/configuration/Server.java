/*     Copyright 2017 Igor Sedov
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

package com.coherentreceiver.analytics.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Server {

    //Server element for configuration
    @XmlElement(name="id")
    int serverId;

    @XmlElement(name="statsurl")
    String statsURL;

    @XmlElement(name="listenerurl")
    String listenerURL;

    @XmlElement(name="login")
    String login;

    @XmlElement(name="password")
    String password;

    @XmlElement(name="mountpointsgaaccounts")
    List<MountpointsGAAccount> mountpointsGAAccounts;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getStatsURL() {
        return statsURL;
    }

    public void setStatsURL(String statsURL) {
        this.statsURL = statsURL;
    }

    public String getListenerURL() {
        return listenerURL;
    }

    public void setListenerURL(String listenerURL) {
        this.listenerURL = listenerURL;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MountpointsGAAccount> getMountpointsGAAccounts() {
        return mountpointsGAAccounts;
    }

    public void setMountpointsGAAccounts(List<MountpointsGAAccount> mountpointsGAAccounts) {
        this.mountpointsGAAccounts = mountpointsGAAccounts;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverId=" + serverId +
                ", statsURL='" + statsURL + '\'' +
                ", listenerURL='" + listenerURL + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mountpointsGAAccounts=" + mountpointsGAAccounts +
                '}';
    }
}


