package com.coherentreceiver.analytics.geo;

import com.coherentreceiver.analytics.platform.streaminganalytics.service.SingleListenerMeasurementService;
import com.maxmind.geoip2.GeoIp2Provider;
import com.maxmind.geoip2.model.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Optional;

@Component
public class GeoProviderService {

    private final Logger logger = LoggerFactory.getLogger(GeoProviderService.class);

    @Autowired
    @Qualifier("geoprovider")
    GeoIp2Provider geoProvider;

    public CityResponse getByIP (String IPAddress){

        CityResponse response = null;
        try {
            if (geoProvider != null) {
                InetAddress ip = InetAddress.getByName(IPAddress);
                response = geoProvider.city(ip);
            }
        } catch (Exception ex) {
            logger.info("IP Address not found in the database or Unknown host");
        }
        return response;
    }

    public String getCountry (String IPAddress) {
        CityResponse cityResponse = getByIP(IPAddress);
        String country="";
        try {
            country = cityResponse.getCountry().getName();
        } catch (Exception ex){
            logger.info("No country found for IP Address " + IPAddress);
        }
        return country;
    }

    public String getCity (String IPAddress){
        CityResponse cityResponse = getByIP(IPAddress);
        String city="";
        try {
            city = cityResponse.getCity().getName();
        } catch (Exception ex){
            logger.info("No country found for IP Address " + IPAddress);
        }
        return city;
    }

}
