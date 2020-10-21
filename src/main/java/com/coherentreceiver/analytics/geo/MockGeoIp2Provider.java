package com.coherentreceiver.analytics.geo;

import com.maxmind.geoip2.GeoIp2Provider;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

import java.io.IOException;
import java.net.InetAddress;

public class MockGeoIp2Provider implements GeoIp2Provider {
    @Override
    public CountryResponse country(InetAddress inetAddress) throws IOException, GeoIp2Exception {
        return null;
    }

    @Override
    public CityResponse city(InetAddress inetAddress) throws IOException, GeoIp2Exception {
        return null;
    }

}
