/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients;

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
