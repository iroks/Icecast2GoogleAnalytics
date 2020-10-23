package com.coherentreceiver.analytics.platform.streaminganalytics.service;

import com.coherentreceiver.analytics.geo.GeoProviderService;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.platform.streaminganalytics.dao.InfluxDBRepository;
import com.coherentreceiver.analytics.platform.streaminganalytics.model.SingleListenerMeasurement;
import com.maxmind.geoip2.GeoIp2Provider;
import com.maxmind.geoip2.model.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.time.Instant;
import java.util.Optional;

@Service
public class SingleListenerMeasurementService <T, ID> {

    private final Logger logger = LoggerFactory.getLogger(SingleListenerMeasurementService.class);

    @Autowired
    InfluxDBRepository<SingleListenerMeasurement, Instant> influxDBRepository;

    @Autowired
    GeoProviderService geoProviderService;

    public void saveAll(StreamProperty sp, Listeners listeners) {

        for (SingleListenerElement singleListener : listeners.getSource().getListeners()) {
            SingleListenerMeasurement slMeasurement = new SingleListenerMeasurement(singleListener);

            String remoteIP = slMeasurement.getIp();

            slMeasurement.setCountry(Optional.ofNullable(geoProviderService.getCountry(remoteIP)).orElse(""));
            slMeasurement.setCity(Optional.ofNullable(geoProviderService.getCity(remoteIP)).orElse(""));

            slMeasurement.setTitle(Optional.ofNullable(sp.getCharacterDecoder().decode(sp.getTitle())).orElse(""));
            slMeasurement.setMountpoint(Optional.ofNullable((sp.getMountPoint())).orElse(""));
            slMeasurement.setIp(Optional.ofNullable (remoteIP).orElse(""));
            slMeasurement.setGaAccount(Optional.ofNullable(sp.getGaAccount()).orElse(""));

            influxDBRepository.save(slMeasurement);



        }
    }
}