package com.coherentreceiver.analytics.platform.customanalytics.model;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Measurement(name = "icecast_listeners", database = "icecast", timeUnit = TimeUnit.SECONDS)
public class ListenersMeasurement {
    @TimeColumn(timeUnit = java.util.concurrent.TimeUnit.MILLISECONDS)
    private Instant time;

    @Column (name = "mountpoint", tag = true)
    private String mountPoint;

    @Column (name = "num_listeners")
    private int numListeners;

    @Column (name = "ga_account", tag = true)
    private String gaAccount;

    @Column (name = "title", tag = true)
    private String title;

    @Column (name = "client_connections")
    private int clientConnections;

    @Column (name = "clients")
    private int clients;

    @Column(name ="listener_connections")
    private int listenerConnections;

    @Column(name ="listeners")
    private int listeners;

    @Column(name = "server_id", tag = true)
    private String serverId;

    @Column (name = "server_start")
    String serverStart;

    @Column (name = "sources")
    int sources;

    @Column (name = "server_host")
    String serverHost;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public int getNumListeners() {
        return numListeners;
    }

    public void setNumListeners(int numListeners) {
        this.numListeners = numListeners;
    }

    public String getGaAccount() {
        return gaAccount;
    }

    public void setGaAccount(String gaAccount) {
        this.gaAccount = gaAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClientConnections() {
        return clientConnections;
    }

    public void setClientConnections(int clientConnections) {
        this.clientConnections = clientConnections;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    public int getListenerConnections() {
        return listenerConnections;
    }

    public void setListenerConnections(int listenerConnections) {
        this.listenerConnections = listenerConnections;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerStart() {
        return serverStart;
    }

    public void setServerStart(String serverStart) {
        this.serverStart = serverStart;
    }

    public int getSources() {
        return sources;
    }

    public void setSources(int sources) {
        this.sources = sources;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }
}
