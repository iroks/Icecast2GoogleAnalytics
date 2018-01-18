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

package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.icecastmodel.logs.IcecastAccessLogParser;
import com.coherentreceiver.analytics.icecastmodel.stats.StreamProperty;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class IcecastLogParserTask extends AbstractTask <StreamProperty> implements Runnable {

    public IcecastLogParserTask (){}

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IcecastAccessLogParser.class);

    public void run() {
        logger.debug ("Log parser; connected to the server");
        try {

            //TODO: logging is not included in this version
            logger.debug("Log parsing complete; transfer data to the memory list");

        }catch (Exception e) {}
    }
}
