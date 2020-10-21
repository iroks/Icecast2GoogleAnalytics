package com.coherentreceiver.analytics.geo;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.GeoIp2Provider;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;

// https://blog.maxmind.com/2019/12/18/significant-changes-to-accessing-and-using-geolite2-databases/
// https://db-ip.com/db/download/ip-to-city-lite
@Configuration
public class MaxMindConfig {

 //   @Value("${spring.application.maxminddb.path:''}")
    @Value ("#{appconfiguration.geoDatabasePath}")
    private String dbLocation;

 //   @Value("${spring.application.maxminddb.databasebean}")
    @Value ("#{appconfiguration.geolocationProvder}")
    private String databasebean;

    private GeoIp2Provider databaseReader;

    @Autowired
    public void setGeoIp2Provider(ApplicationContext context) {
        databaseReader = (GeoIp2Provider) context.getBean(databasebean);
    }

    @Bean (name="geoprovider")
    public GeoIp2Provider geoIp2Provider (){
        return databaseReader;
    }

    @Bean (name="maxmind")
    public DatabaseReader maxMindDB() {
        File database = new File(dbLocation);
        DatabaseReader dbReader = null;
        try {
            dbReader =  new DatabaseReader.Builder(database).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dbReader;
    }

    @Bean (name = "mockmaxmind")
    public MockGeoIp2Provider mockDBReader () {
        MockGeoIp2Provider mockReader = null;
        try {
            mockReader =  new MockGeoIp2Provider();
        }catch (Exception e){}

        return mockReader;
    }

}



