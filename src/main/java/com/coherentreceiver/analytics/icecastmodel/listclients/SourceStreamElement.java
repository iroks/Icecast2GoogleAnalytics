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


package com.coherentreceiver.analytics.icecastmodel.listclients;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Set;

/**
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class SourceStreamElement {

    //Number of listners that listen this mount point as a number
    @XmlElement(name="Listeners")
    int numListeners;

    //List of the Listner elements
    @XmlElement (name="listener")
    Set<SingleListenerElement> listeners;

    @XmlAttribute(name="mount")
    String mountPoint;

    public int getNumListeners() {
        return numListeners;
    }

    public void setNumListeners(int numListeners) {
        this.numListeners = numListeners;
    }

    public Set<SingleListenerElement> getListeners() {
        return listeners;
    }

    public void setListeners(Set<SingleListenerElement> listeners) {
        this.listeners = listeners;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }
}
