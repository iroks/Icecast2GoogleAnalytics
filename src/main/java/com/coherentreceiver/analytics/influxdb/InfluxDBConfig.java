package com.coherentreceiver.analytics.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * InfluxDb To configure
 * Creator Java Notes
 * URL https://blog.52itstyle.vip
 */

@Configuration
public class InfluxDBConfig {

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
            /**
             * Asynchronous insertion:
             * enableBatch Here the first is the number of point s, the second is the time in milliseconds
             * point Number and time are used in combination if 100 or 2000 milliseconds are full
             * A write request is sent when either condition is met.
             */
            influxDB.setDatabase(database)
                    .enableBatch(100,2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            influxDB.setRetentionPolicy("autogen");
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }
}