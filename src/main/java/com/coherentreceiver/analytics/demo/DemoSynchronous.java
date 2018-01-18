package com.coherentreceiver.analytics.demo;

import com.coherentreceiver.analytics.configuration.Configuration;
import com.coherentreceiver.analytics.configuration.Configurator;
import com.coherentreceiver.analytics.task.Init;
import com.coherentreceiver.analytics.task.InitSynchronous;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */

    public class DemoSynchronous {

        public static void main (String[] args){

            final Logger logger = LoggerFactory.getLogger(DemoSynchronous.class);

            logger.info("error {}", logger.isErrorEnabled());
            logger.info("info {}", logger.isInfoEnabled());
            logger.info("debug {}", logger.isDebugEnabled());


            String configurationPath="";

            if (args.length == 0) {

                configurationPath = "./resources/config.xml";

            }else {
                configurationPath = args[0];
            }

            Configurator configurator = new Configurator(configurationPath);

            Configuration configuration = configurator.getConfiguration();

            InitSynchronous init = new InitSynchronous(configuration);

            logger.debug("done");

        }
    }

