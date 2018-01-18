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
package com.coherentreceiver.analytics.configuration;

import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 *
 */
public class Configurator {
    private String configurationPath = "";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Configurator.class);

    public Configurator(String configurationPath) {
        this.configurationPath = configurationPath;

    }

    public Configuration getConfiguration() {

        Configuration configuration = null;

        try {


            JAXBContext jc = JAXBContext.newInstance(Configuration.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File xml = new File(configurationPath);
            configuration = (Configuration) unmarshaller.unmarshal(xml);

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return configuration;

    }

}
