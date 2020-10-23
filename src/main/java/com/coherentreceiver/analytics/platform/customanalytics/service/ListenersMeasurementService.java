package com.coherentreceiver.analytics.platform.customanalytics.service;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.platform.customanalytics.dao.InfluxDBRepository;
import com.coherentreceiver.analytics.platform.customanalytics.model.ListenersMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ListenersMeasurementService <T, ID> {

    private final Logger logger = LoggerFactory.getLogger(ListenersMeasurementService.class);

    @Autowired
    InfluxDBRepository<ListenersMeasurement, Instant> influxDBRepository;

        public void save (StreamProperty sp, Listeners listeners){

            //todo: if really need all these values on transfer objects?
            ListenersMeasurement listenersMeasurement = new ListenersMeasurement();
            listenersMeasurement.setTime(Instant.now());
            listenersMeasurement.setMountPoint(
                    Optional.ofNullable(listeners.getSource().getMountPoint()).orElse("")
            );
            listenersMeasurement.setNumListeners(listeners.getSource().getNumListeners());
            listenersMeasurement.setGaAccount(sp.getGaAccount());
            listenersMeasurement.setTitle(sp.getTitle());
            listenersMeasurement.setClientConnections(sp.getServerStats().getClientConnections());
            listenersMeasurement.setClients(sp.getServerStats().getClients());
            listenersMeasurement.setListenerConnections(sp.getServerStats().getListenerConnections());
            listenersMeasurement.setListeners(sp.getServerStats().getListeners());
            listenersMeasurement.setServerId(sp.getServerStats().getServerId());
            listenersMeasurement.setServerStart(sp.getServerStats().getServerStart());
            listenersMeasurement.setSources(sp.getServerStats().getSources());
            listenersMeasurement.setServerHost(sp.getServerStats().getHost());

            influxDBRepository.save(listenersMeasurement);

    }
}
