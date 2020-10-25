/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.parser;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Stream;

import java.net.URI;
import java.util.List;

/**
 *
 */
public interface Parser {

        List<Stream> parse(URI uri) throws ParseException;

}
