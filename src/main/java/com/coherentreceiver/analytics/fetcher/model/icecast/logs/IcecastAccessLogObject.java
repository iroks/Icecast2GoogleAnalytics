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

package com.coherentreceiver.analytics.fetcher.model.icecast.logs;

import java.time.LocalDateTime;

/**
 *
 * https://www.npmjs.com/package/icecast-log-parser
 */
public class IcecastAccessLogObject {

    private String ip; //client ip address
    private String client; //client?
    private String user; //user for authenicated users
    private LocalDateTime datetime; //time stamp from log
    private String method; //HTTP method
    private String url; //requested URL path
    private String protocol; //used protocol
    private int status; //http status response code
    private long bytesSent; //the size in bytes of the stream data returned to the client
    private String refer; //Referrer url
    private String agent; //User-agent
    private int duration; //connection duration

    public IcecastAccessLogObject(String ip, String client, String user, LocalDateTime datetime, String method, String url, String protocol, int status, long bytesSent, String refer, String agent, int duration) {
        this.ip = ip;
        this.client = client;
        this.user = user;
        this.datetime = datetime;
        this.method = method;
        this.url = url;
        this.protocol = protocol;
        this.status = status;
        this.bytesSent = bytesSent;
        this.refer = refer;
        this.agent = agent;
        this.duration = duration;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return datetime;
    }

    public void setDateTime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "IcecastAccessLogObject{" +
                "ip='" + ip + '\'' +
                ", client='" + client + '\'' +
                ", user='" + user + '\'' +
                ", timestamp='" + datetime + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", status='" + status + '\'' +
                ", bytesSent='" + bytesSent + '\'' +
                ", refer='" + refer + '\'' +
                ", agent='" + agent + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
