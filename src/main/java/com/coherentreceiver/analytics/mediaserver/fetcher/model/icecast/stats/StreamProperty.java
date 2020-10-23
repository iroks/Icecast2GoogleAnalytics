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

package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats;

import com.coherentreceiver.analytics.appconfiguration.model.Server;
import com.coherentreceiver.analytics.helper.idgenerator.IDGenerator;
import com.coherentreceiver.analytics.helper.decoding.Decoder;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;

import java.time.LocalDateTime;

/**
 *
 */

public class StreamProperty {

    private String mountPoint;
    private String title;
    private String gaAccount;
    private int serverId;

    private Server server;
    private ServerStats serverStats;
    private IDGenerator idGenerator;
    private Decoder characterDecoder;
    private Listeners listeners;
    private LocalDateTime timeStamp;

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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Listeners getListeners() {
        return listeners;
    }

    public void setListeners(Listeners listeners) {
        this.listeners = listeners;
    }

    public IDGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

//    public int getServerId() {
//        return server.getServerId();
//    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Decoder getCharacterDecoder() {
        return characterDecoder;
    }

    public void setCharacterDecoder(Decoder characterDecoder) {
        this.characterDecoder = characterDecoder;
    }

    public ServerStats getServerStats() {
        return serverStats;
    }

    public void setServerStats(ServerStats serverStats) {
        this.serverStats = serverStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreamProperty that = (StreamProperty) o;

        if (serverId != that.serverId) return false;
        if (!mountPoint.equals(that.mountPoint)) return false;
        if (!title.equals(that.title)) return false;
        return gaAccount.equals(that.gaAccount);

    }

    @Override
    public int hashCode() {
        int result = mountPoint.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + gaAccount.hashCode();
        result = 31 * result + serverId;
        return result;
    }
}
