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

package com.coherentreceiver.analytics.fetcher.model.icecast.stats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RadioStream {


    @XmlAttribute(name="mount")
    String mountPoint;

    @XmlElement(name="audio_channels")
    String audioChanels;


    @XmlElement(name="audio_info")
    String audioInfo;

    @XmlElement(name="audio_samplerate")
    String audioSamplerate;

    @XmlElement(name="channels")
    int channels;

    @XmlElement(name="genre")
    String genre;

    @XmlElement(name="ice-bitrate")
    String iceBitrate;


    @XmlElement(name="listener_peak")
    int listenerPeak;


    @XmlElement(name="listeners")
    int listeners;

    @XmlElement(name="listenurl")
    String listenurl;

    @XmlElement(name="max_listeners")
    String maxListeners;

    @XmlElement(name="public")
    int publicStream;

    @XmlElement(name="quality")
    int quality;


    @XmlElement(name="samplerate")
    int samplerate;

    @XmlElement(name="server_description")
    String serverDescritpion;

    @XmlElement(name="server_name")
    String serverName;

    @XmlElement(name="server_type")
    String serverType;

    @XmlElement(name="server_url")
    String serverURL;

    @XmlElement(name="slow_listeners")
    int slowListeners;


    @XmlElement(name="source_ip")
    String sourceIP;

    @XmlElement(name="stream_start")
    String streamStart;

    @XmlElement(name="stream_start_iso8601")
    String streamStartISO8601;

    @XmlElement(name="total_bytes_read")
    long totalBytesRead;

    @XmlElement(name="total_bytes_sent")
    long totalBytesSent;

    @XmlElement(name="user_agent")
    long userAgent;


    @XmlElement(name="title")
    String title;

    @XmlElement(name="artist")
    String artist;


    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getAudioChanels() {
        return audioChanels;
    }

    public void setAudioChanels(String audioChanels) {
        this.audioChanels = audioChanels;
    }

    public String getAudioInfo() {
        return audioInfo;
    }

    public void setAudioInfo(String audioInfo) {
        this.audioInfo = audioInfo;
    }

    public String getAudioSamplerate() {
        return audioSamplerate;
    }

    public void setAudioSamplerate(String audioSamplerate) {
        this.audioSamplerate = audioSamplerate;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIceBitrate() {
        return iceBitrate;
    }

    public void setIceBitrate(String iceBitrate) {
        this.iceBitrate = iceBitrate;
    }

    public int getListenerPeak() {
        return listenerPeak;
    }

    public void setListenerPeak(int listenerPeak) {
        this.listenerPeak = listenerPeak;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getListenurl() {
        return listenurl;
    }

    public void setListenurl(String listenurl) {
        this.listenurl = listenurl;
    }

    public String getMaxListeners() {
        return maxListeners;
    }

    public void setMaxListeners(String maxListeners) {
        this.maxListeners = maxListeners;
    }

    public int getPublicStream() {
        return publicStream;
    }

    public void setPublicStream(int publicStream) {
        this.publicStream = publicStream;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getSamplerate() {
        return samplerate;
    }

    public void setSamplerate(int samplerate) {
        this.samplerate = samplerate;
    }

    public String getServerDescritpion() {
        return serverDescritpion;
    }

    public void setServerDescritpion(String serverDescritpion) {
        this.serverDescritpion = serverDescritpion;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public int getSlowListeners() {
        return slowListeners;
    }

    public void setSlowListeners(int slowListeners) {
        this.slowListeners = slowListeners;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getStreamStart() {
        return streamStart;
    }

    public void setStreamStart(String streamStart) {
        this.streamStart = streamStart;
    }

    public String getStreamStartISO8601() {
        return streamStartISO8601;
    }

    public void setStreamStartISO8601(String streamStartISO8601) {
        this.streamStartISO8601 = streamStartISO8601;
    }

    public long getTotalBytesRead() {
        return totalBytesRead;
    }

    public void setTotalBytesRead(long totalBytesRead) {
        this.totalBytesRead = totalBytesRead;
    }

    public long getTotalBytesSent() {
        return totalBytesSent;
    }

    public void setTotalBytesSent(long totalBytesSent) {
        this.totalBytesSent = totalBytesSent;
    }

    public long getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(long userAgent) {
        this.userAgent = userAgent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "RadioStream{" +
                "mountPoint='" + mountPoint + '\'' +
                ", audioChanels='" + audioChanels + '\'' +
                ", audioInfo='" + audioInfo + '\'' +
                ", audioSamplerate='" + audioSamplerate + '\'' +
                ", channels=" + channels +
                ", genre='" + genre + '\'' +
                ", iceBitrate='" + iceBitrate + '\'' +
                ", listenerPeak=" + listenerPeak +
                ", listeners=" + listeners +
                ", listenurl='" + listenurl + '\'' +
                ", maxListeners='" + maxListeners + '\'' +
                ", publicStream=" + publicStream +
                ", quality=" + quality +
                ", samplerate=" + samplerate +
                ", serverDescritpion='" + serverDescritpion + '\'' +
                ", serverName='" + serverName + '\'' +
                ", serverType='" + serverType + '\'' +
                ", serverURL='" + serverURL + '\'' +
                ", slowListeners=" + slowListeners +
                ", sourceIP='" + sourceIP + '\'' +
                ", streamStart='" + streamStart + '\'' +
                ", streamStartISO8601='" + streamStartISO8601 + '\'' +
                ", totalBytesRead=" + totalBytesRead +
                ", totalBytesSent=" + totalBytesSent +
                ", userAgent=" + userAgent +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
