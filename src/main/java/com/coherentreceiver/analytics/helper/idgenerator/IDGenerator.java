/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.helper.idgenerator;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;

/**
 *
 */
public interface IDGenerator {

    public String getId(SingleListenerElement listener);

}
