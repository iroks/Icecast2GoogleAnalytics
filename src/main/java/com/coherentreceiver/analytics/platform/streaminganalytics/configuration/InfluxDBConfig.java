package com.coherentreceiver.analytics.platform.streaminganalytics.configuration;

import com.coherentreceiver.analytics.geo.GeoProviderService;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class InfluxDBConfig {

    private final Logger logger = LoggerFactory.getLogger(InfluxDBConfig.class);

    @Value("${spring.influx.url:''}")
    private String influxDBUrl;

    @Value("${spring.influx.user:''}")
    private String userName;

    @Value("${spring.influx.password:''}")
    private String password;

    @Value("${spring.influx.database:''}")
    private String database;

    @Bean
    public InfluxDB influxDB(){
        InfluxDB influxDB = InfluxDBFactory.connect(influxDBUrl, userName, password);
        try {
            Pong response = influxDB.ping();
            if (response.getVersion().equalsIgnoreCase("unknown")) {
                logger.error("Error pinging influxdb database server.");
                //todo: throw Exception and handling
            }
            influxDB.setDatabase(database)
                    .enableBatch(100,2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            influxDB.createDatabase(database);
            e.printStackTrace();
        } finally {
            influxDB.setRetentionPolicy("autogen");
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }
}