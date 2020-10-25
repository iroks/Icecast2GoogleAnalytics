/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.helper.idgenerator;

/**
 *
 */
public class IDGeneratorFactory  {

    public static IDGenerator getIDGenerator (String algorithm) throws Exception{
        if (algorithm.compareTo("icecast_id") ==0) return new IDGeneratorIcecastID();
        if (algorithm.compareTo("anonymized_ip") ==0) return new IDGeneratorAnonymizedIP();
        if (algorithm.compareTo("ip")==0) return new IDGeneratorIP();

        throw new Exception("unsupported ID generation method: available options are: anonymizedIP, IcecastId, ip");

    }
}
