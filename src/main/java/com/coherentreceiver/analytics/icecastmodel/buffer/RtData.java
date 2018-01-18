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

package com.coherentreceiver.analytics.icecastmodel.buffer;

import com.coherentreceiver.analytics.icecastmodel.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.icecastmodel.listclients.SingleListenerState;
import com.coherentreceiver.analytics.icecastmodel.stats.StreamProperty;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 *
 */
public class RtData<T extends StreamProperty> {

    private ConcurrentMap<T,T> rtData = new ConcurrentHashMap<>();
    private Semaphore sem = new Semaphore (1);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RtData.class);


    public ConcurrentMap<T, T> getRtData() throws Exception {

        return rtData;
    }

    public void lock() throws Exception{

        sem.acquire();

    }

    public void unlock() throws Exception{

        sem.release();

    }

    public void setRtData(ConcurrentMap<T, T> rtData) {this.rtData=rtData;}

    public void removeAll () throws Exception {

        rtData.clear();
    }

    public boolean contain (T newData){

        return (rtData.containsKey(newData)) ? true : false;
    }

    public void add (T newData) throws Exception {
        //add


    }

    public void mergeOne (T newData) throws Exception{

     //merge one


    }


    public void mergeAll (Map<T,T> newData) throws Exception{

        /*
        merge section
         */

    }

    public T mergeListeners (T oldData, T newData) {


        //merge section
        T t = null;
        return t;

    }

}



