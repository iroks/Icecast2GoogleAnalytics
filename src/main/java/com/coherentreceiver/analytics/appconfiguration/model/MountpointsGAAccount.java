/**
 * Copyright 2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.appconfiguration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MountpointsGAAccount {

    @JsonProperty(value="mountpoint")
    String mountPoint;

    @JsonProperty (value="gaaccount")
    String gaAccount;

    @JsonProperty (value="user_id_algorithm")
    String userIdAlgorithm;

    @JsonProperty (value="character-decoding")
    String characterDecoding;


    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getGaAccount() {
        return gaAccount;
    }

    public void setGaAccount(String gaAccount) {
        this.gaAccount = gaAccount;
    }

    public String getUserIdAlgorithm() {
        return userIdAlgorithm;
    }

    public void setUserIdAlgorithm(String userIdAlgorithm) {
        this.userIdAlgorithm = userIdAlgorithm;
    }

    public String getCharacterDecoding() {
        return characterDecoding;
    }

    public void setCharacterDecoding(String characterDecoding) {
        this.characterDecoding = characterDecoding;
    }

    @Override
    public String toString() {
        return "MountpointsGAAccount{" +
                "mountPoint='" + mountPoint + '\'' +
                ", gaAccount='" + gaAccount + '\'' +
                ", userIdAlgorithm='" + userIdAlgorithm + '\'' +
                ", characterDecoding='" + characterDecoding + '\'' +
                '}';
    }
}
