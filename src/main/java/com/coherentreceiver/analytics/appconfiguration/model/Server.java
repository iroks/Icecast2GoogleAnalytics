/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.appconfiguration.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Server {

    //Server element for configuration


    @JacksonXmlProperty(localName="id")
    Integer serverId;

    @JacksonXmlProperty(localName="baseurl")
    String baseURL;


    @JacksonXmlProperty(localName = "statsurl")
    String statsURL;

    @JacksonXmlProperty(localName = "listenerurl")
    String listenerURL;

    @JacksonXmlProperty(localName = "login")
    String login;

    @JacksonXmlProperty(localName = "password")
    @JsonProperty (value="password")
    String password;

    @JacksonXmlProperty(localName = "mountpointsgaaccounts")
    List<MountpointsGAAccount> mountpointsGAAccounts;

    public Server(){}

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
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



