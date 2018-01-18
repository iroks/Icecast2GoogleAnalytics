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




