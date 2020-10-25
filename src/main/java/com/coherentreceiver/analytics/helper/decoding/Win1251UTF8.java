/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.helper.decoding;

import org.slf4j.LoggerFactory;

/**
 *
 */
public class Win1251UTF8 implements Decoder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Win1251UTF8.class);

    public String decode(String str) {

        String stringResult="";

        //todo:
        //this must be re-written
        try {
            byte ptext[] = str.getBytes("UTF-8");
            byte result[] = new byte[ptext.length];

            int j = 0;
            for (int i = 0; i < ptext.length; i++) {
                if ((byte) ptext[i] == (byte) 0xC3
                        && ((byte) ptext[i + 1] == (byte) 0x90 || ptext[i + 1] == (byte) 0x91)
                        && (byte) ptext[i + 2] == (byte) 0xC2) {
                    result[j] = (byte) (ptext[i + 1] + 0x40);
                    j++;
                    result[j] = (byte) ptext[i + 3];
                    j++;
                    i = i + 3;
                } else {
                    result[j] = ptext[i];
                    j++;
                }

            }

            stringResult = new String(result, "UTF-8");

        } catch (Exception e) {
            logger.error(e.toString());
        }


        return stringResult;
    }

}




