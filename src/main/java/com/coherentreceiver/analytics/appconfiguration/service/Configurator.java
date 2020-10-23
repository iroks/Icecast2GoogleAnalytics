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
package com.coherentreceiver.analytics.appconfiguration.service;

import com.coherentreceiver.analytics.appconfiguration.model.Config;
import com.coherentreceiver.analytics.helper.xml.XMLConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@ComponentScan
public class Configurator {

    private static final Logger log = LoggerFactory.getLogger(Configurator.class);

    @Value("${icecast2ga.configfile}")
    private String configurationfile;


    @Bean (name="appconfiguration")
    public Config getConfiguration() {
        log.info("Loading configuration file " + configurationfile);
        Config appConfig=null;
        try {
            Class<Config> c = Config.class;
            appConfig = new XMLConverter().readConfigurationFromFile(configurationfile, c);

        } catch (Exception e) {
            e.printStackTrace();
            // it makes no sense to continue without configuration file
            System.exit(-1);
        }
        return appConfig;
    }

    public String getConfigurationfile() {
        return configurationfile;
    }

    public void setConfigurationfile(String configurationfile) {
        this.configurationfile = configurationfile;
    }
}
