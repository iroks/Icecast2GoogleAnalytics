/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.service;

import java.io.InputStream;

/**
 *
 */
public interface HTTPFetcher {

    InputStream fetch(String URL, String login, String password);
}
