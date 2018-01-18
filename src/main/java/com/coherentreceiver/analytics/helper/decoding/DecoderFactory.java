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


