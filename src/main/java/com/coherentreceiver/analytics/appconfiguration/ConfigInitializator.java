/**
 * Copyright 2020 SWI Kommunikations- und Computer GmbH
 */


package com.coherentreceiver.analytics.appconfiguration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableCaching
public class ConfigInitializator {

    private static final Logger log = LoggerFactory.getLogger(ConfigInitializator.class);

    @PostConstruct
    private void init() {
    }


}