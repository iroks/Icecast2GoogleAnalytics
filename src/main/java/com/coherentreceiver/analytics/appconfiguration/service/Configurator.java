/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
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
