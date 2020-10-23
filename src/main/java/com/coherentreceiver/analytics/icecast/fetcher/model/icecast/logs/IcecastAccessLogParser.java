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

package com.coherentreceiver.analytics.icecast.fetcher.model.icecast.logs;

import com.coherentreceiver.analytics.icecast.fetcher.service.SecureGetFetcher;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class IcecastAccessLogParser implements AccessLogParser {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IcecastAccessLogParser.class);
    private static final String LOG_ENTRY_PATTERN =
            // 1:IP
            // 2:client
            // 3:user
            // 4:time stamp
            // 5:method (e.g. GET)
            // 6:req url (relevant for mountpoint)
            // 7:protocol
            // 8:response status code
            // 9:bytes sent (the number of bytes that was sent by this request)
            //10: ???
            //11: user-agent
            //12: listened seconds
            ////127.0.0.1[1] -[2] steve[3] [05/Apr/2017:13:01:48 +0300][4] "GET[5] /admin/listclients?mount=/radio1[6] HTTP/1.1[7]" 200[8] 6570[9] "-"[10] "Apache-HttpClient/4.5.3 (Java/1.8.0_91)"[11] 0[12]

            "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\S+) (\\S+) (\\S+) \"(.+)\" (\\d+)";
    //             1       2      3                      4                5      6       7        8      9     10      11     12
    private static final Pattern PATTERN = Pattern.compile(LOG_ENTRY_PATTERN);

    private static final String LOG_DATE_FORMAT = "dd/LLL/yyyy:kk:mm:ss Z"; //07/Apr/2017:11:34:06 +0300
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOG_DATE_FORMAT);


    public IcecastAccessLogObject parseLine (String logline){


            Matcher m = PATTERN.matcher(logline);
            if (!m.find()) {
                logger.debug("Cannot parse logline" + logline);
                throw new RuntimeException("Error parsing logline");
            }


        try {

            LocalDateTime localDateTime = LocalDateTime.parse(m.group(4), formatter); //:11:18:40 +0300");
            int respCode = Integer.parseInt(m.group(8));
            long bytesSent = Long.parseLong(m.group(9));
            int duration = Integer.parseInt(m.group(12));

            IcecastAccessLogObject obj = new  IcecastAccessLogObject (m.group(1), m.group(2), m.group(3), localDateTime,
                    m.group(5), m.group(6), m.group(7), respCode, bytesSent , m.group(10), m.group(11), duration);
            return obj;

        }
        catch (Exception e){
            logger.error("Error parsing the logline during the values decoding:" + logline);
            logger.error(e.toString());
            throw new RuntimeException("Error parsing logline");
        }

    }

    public List<IcecastAccessLogObject> parseAccessLog (InputStream logLines){

        List<IcecastAccessLogObject> logObjects = new ArrayList<>();
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(logLines));


        try {
            while ((line = in.readLine()) != null) {

                IcecastAccessLogObject icecastAccessLogObject=null;
             try {
                 logger.debug("Line to parse: " + line);
                 icecastAccessLogObject = parseLine(line);
             }catch (Exception e){
                                    logger.error(e.toString());
                                    continue;}

                logObjects.add(icecastAccessLogObject);
                logger.debug ("icecast object " + icecastAccessLogObject);


            }


        }catch (Exception e){logger.error(e.toString());

                                }

        return logObjects;

    }

    public static void main (String[] args){
        logger.debug ("TEST");
        IcecastAccessLogParser icecastAccessLogParser = new IcecastAccessLogParser();

        SecureGetFetcher fetcher = new SecureGetFetcher();

        InputStream in = fetcher.fetch("http://...../admin/showlog.txt?log=accesslog", "login", "password");

        List<IcecastAccessLogObject> icecastObjects = icecastAccessLogParser.parseAccessLog(in);

         for (IcecastAccessLogObject icecastObject : icecastObjects) {
             logger.debug (icecastObject.toString());

        }

    }
}
