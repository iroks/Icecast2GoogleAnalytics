/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.helper.decoding;

/**
 *
 */
public class NoDecoder implements Decoder {

    public String decode (String str){
        return str;
    }
}
