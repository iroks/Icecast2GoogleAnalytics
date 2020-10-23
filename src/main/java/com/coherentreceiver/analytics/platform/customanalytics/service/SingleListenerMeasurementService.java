package com.coherentreceiver.analytics.platform.customanalytics.service;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.platform.customanalytics.dao.InfluxDBRepository;
import com.coherentreceiver.analytics.platform.customanalytics.model.SingleListenerMeasurement;
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
    @Qualifier("geoprovider")
    GeoIp2Provider geoProvider;

    public void saveAll(StreamProperty sp, Listeners listeners) {

        for (SingleListenerElement singleListener : listeners.getSource().getListeners()) {
            SingleListenerMeasurement slMeasurement = new SingleListenerMeasurement(singleListener);

            CityResponse response = null;
            try {
                if (geoProvider != null) {
                    InetAddress ip = InetAddress.getByName(slMeasurement.getIp());
                    response = geoProvider.city(ip);
                }
            } catch (Exception ex) {
                logger.info("IP Address not found in the database or Unknown host");
            }

            try {
                slMeasurement.setCountry(Optional.ofNullable(response.getCountry().getName()).orElse(""));
            } catch (Exception ex) {
                slMeasurement.setCountry("");
            }

            try {
                slMeasurement.setCity(Optional.ofNullable(response.getCity().getName()).orElse(""));
            } catch (Exception ex) {
                slMeasurement.setCity("");
            }

            try {
                slMeasurement.setTitle(Optional.ofNullable(sp.getCharacterDecoder().decode(sp.getTitle())).orElse(""));
                slMeasurement.setMountpoint(Optional.ofNullable((sp.getMountPoint())).orElse(""));
                slMeasurement.setServerHost(Optional.ofNullable(sp.getServerStats().getHost()).orElse(""));
                slMeasurement.setGaAccount(Optional.ofNullable(sp.getGaAccount()).orElse(""));

                influxDBRepository.save(slMeasurement);

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }
}