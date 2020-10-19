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


package com.coherentreceiver.analytics.fetcher.model.icecast.listclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * http://.../admin/listclients
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="icestats")

public class Listeners {

    @XmlElement (name="source")
    SourceStreamElement source;

    public SourceStreamElement getSource() {
        return source;
    }

    public void setSource(SourceStreamElement source) {
        this.source = source;
    }
}
