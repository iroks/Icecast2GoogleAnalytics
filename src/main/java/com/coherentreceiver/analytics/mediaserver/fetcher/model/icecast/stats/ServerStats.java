/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="icestats")
public class ServerStats {

    @XmlElement(name="admin")
    String admin;

    @XmlElement(name="client_connections")
    int clientConnections;

    @XmlElement(name="clients")
    int clients;

    @XmlElement(name="connections")
    int connections;

    @XmlElement(name="file_connections")
    int fileConnections;

    @XmlElement(name="host")
    String host;

    @XmlElement(name="listener_connections")
    int listenerConnections;

    @XmlElement(name="listeners")
    int listeners;

    @XmlElement(name="location")
    String location;

    @XmlElement(name="server_id")
    String serverId;

    @XmlElement(name="server_start")
    String serverStart;

    @XmlElement(name="server_start_iso8601")
    String serverStartISO8601;

    @XmlElement(name="source_client_connections")
    int sourceClientConnections;

    @XmlElement(name="source_relay_connections")
    int sourceRelayConnections;

    @XmlElement(name="source_total_connections")
    int sourceTotalConnections;

    @XmlElement(name="sources")
    int sources;

    @XmlElement(name="stats")
    int stats;

    @XmlElement(name="stats_connections")
    int statsConnections;


    @XmlElement (name="source")
    List<RadioStream> radioStreams;


    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    public int getFileConnections() {
        return fileConnections;
    }

    public void setFileConnections(int fileConnections) {
        this.fileConnections = fileConnections;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getServerStartISO8601() {
        return serverStartISO8601;
    }

    public void setServerStartISO8601(String serverStartISO8601) {
        this.serverStartISO8601 = serverStartISO8601;
    }

    public int getSourceClientConnections() {
        return sourceClientConnections;
    }

    public void setSourceClientConnections(int sourceClientConnections) {
        this.sourceClientConnections = sourceClientConnections;
    }

    public int getSourceRelayConnections() {
        return sourceRelayConnections;
    }

    public void setSourceRelayConnections(int sourceRelayConnections) {
        this.sourceRelayConnections = sourceRelayConnections;
    }

    public int getSourceTotalConnections() {
        return sourceTotalConnections;
    }

    public void setSourceTotalConnections(int sourceTotalConnections) {
        this.sourceTotalConnections = sourceTotalConnections;
    }

    public int getSources() {
        return sources;
    }

    public void setSources(int sources) {
        this.sources = sources;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public int getStatsConnections() {
        return statsConnections;
    }

    public void setStatsConnections(int statsConnections) {
        this.statsConnections = statsConnections;
    }

    public List<RadioStream> getRadioStreams() {
        return radioStreams;
    }

    public void setRadioStreams(List<RadioStream> radioStreams) {
        this.radioStreams = radioStreams;
    }


    @Override
    public String toString() {
        return "ServerStats{" +
                "admin='" + admin + '\'' +
                ", clientConnections=" + clientConnections +
                ", clients=" + clients +
                ", connections=" + connections +
                ", fileConnections=" + fileConnections +
                ", host='" + host + '\'' +
                ", listenerConnections=" + listenerConnections +
                ", listeners=" + listeners +
                ", location='" + location + '\'' +
                ", serverId='" + serverId + '\'' +
                ", serverStart='" + serverStart + '\'' +
                ", serverStartISO8601='" + serverStartISO8601 + '\'' +
                ", sourceClientConnections=" + sourceClientConnections +
                ", sourceRelayConnections=" + sourceRelayConnections +
                ", sourceTotalConnections=" + sourceTotalConnections +
                ", sources=" + sources +
                ", stats=" + stats +
                ", statsConnections=" + statsConnections +
                ", radioStreams=" + radioStreams +
                '}';
    }
}
