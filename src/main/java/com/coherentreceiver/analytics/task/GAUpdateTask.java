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


package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.platform.googleanalytics.service.GAService;
import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.stats.StreamPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class GAUpdateTask extends AbstractTask   {

    final Logger logger = LoggerFactory.getLogger(GAUpdateTask.class);

    @Autowired
    private GAService gaService;

    @Scheduled(fixedDelayString = "#{appconfiguration.analyticsUpdateFrequency*1000}")
    public void gaUpdateTask () {
        super.updateTask();
    }

     public void updateSynchronous (StreamProperty sp, Listeners listeners){

           for (SingleListenerElement singleListener : listeners.getSource().getListeners()){
            gaService.makePageHit (sp, singleListener);
        }

    }

    public StreamPropertyService getStreamPropertyService() {
        return streamPropertyService;
    }

    public void setStreamPropertyService(StreamPropertyService streamPropertyService) {
        this.streamPropertyService = streamPropertyService;
    }
}
