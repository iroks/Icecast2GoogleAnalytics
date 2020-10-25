/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */
package com.coherentreceiver.analytics.helper.decoding;

/**
 *
 */
public class DecoderFactory {

    public static Decoder getDecoder (String algorithm) throws Exception{
        if (algorithm.compareTo("no") ==0) return new NoDecoder();
        if (algorithm.compareTo("win1251UTF8") ==0) return new Win1251UTF8();

        throw new Exception("unsupported Decoder method: available options are: no decoder; win1251UTF-8");

    }
}


