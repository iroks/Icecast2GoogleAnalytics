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

package com.coherentreceiver.analytics.helper.idgenerator;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;

/**
 *
 */
public class IDGeneratorAnonymizedIP implements IDGenerator {

    public String getId (SingleListenerElement listener){

        String ip = listener.getIp();

        if (ip.lastIndexOf(".") != -1) {
            //ipv4
            String truncatedIP = ip.substring(0, ip.lastIndexOf("."));
            return truncatedIP + "." + "0" + "-" + listener.getId();
        }

        if (ip.lastIndexOf(":") != -1){
            //ipv6
            String truncatedIP = ip.substring(0, ip.lastIndexOf(":"));
            return truncatedIP + ":" + "0"+ "-" + listener.getId();
        }

        return ip+"-"+listener.getId();

    }

    public static void main (String[] args){

        IDGeneratorAnonymizedIP test = new IDGeneratorAnonymizedIP();
        SingleListenerElement listener = new SingleListenerElement();
        listener.setIp("0.0.0.0");
        System.out.println (test.getId(listener));

        listener.setIp("0:0:0:0:12:25");
        System.out.println (test.getId(listener));
    }
}
