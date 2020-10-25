/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
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
