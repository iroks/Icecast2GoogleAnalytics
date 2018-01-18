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

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MountpointsGAAccount {

    @XmlElement(name="mountpoint")
    String mountPoint;

    @XmlElement(name="gaaccount")
    String gaAccount;

    @XmlElement(name="user_id_algorithm")
    String userIdAlgorithm;

    @XmlElement(name="character-decoding")
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
                ", userId='" + userIdAlgorithm + '\'' +
                '}';
    }
}
