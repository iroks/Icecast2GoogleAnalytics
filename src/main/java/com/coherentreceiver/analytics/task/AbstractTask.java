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

import com.coherentreceiver.analytics.icecastmodel.buffer.RtData;
import com.coherentreceiver.analytics.icecastmodel.stats.StreamProperty;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract  class AbstractTask<T extends StreamProperty> implements Runnable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    protected RtData<T> rtData;

    public RtData<T> getRtData() {
        return rtData;
    }

    public void setRtData(RtData<T> rtData) {
        this.rtData = rtData;
    }





}
