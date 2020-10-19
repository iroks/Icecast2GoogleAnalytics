package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.configuration.model.Config;
import com.coherentreceiver.analytics.configuration.service.Configurator;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.ServerService;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamPropertyService;
import com.coherentreceiver.analytics.influxdb.ListenerMeasurement;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.influxdb.InfluxDB;
import org.influxdb.annotation.Column;
import org.influxdb.dto.Point;
import org.influxdb.impl.InfluxDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class InfluxDBTask extends AbstractTask  {

    final Logger logger = LoggerFactory.getLogger(InfluxDBTask.class);

    @Autowired
    InfluxDB influxDB;

    @Scheduled (fixedDelayString = "#{appconfiguration.influxDBUpdateFrequency*1000}")
    public void setInfluxDBUpdateTask () {

        logger.info ("start influxdb process");
        super.updateTask();
        logger.info("finish influxdb process");
    }

    public void updateSynchronous (StreamProperty sp, Listeners listeners){


        ListenerMeasurement listenerMeasurement = new ListenerMeasurement();
        listenerMeasurement.setTime(Instant.now());
        listenerMeasurement.setMountPoint(listeners.getSource().getMountPoint());
        listenerMeasurement.setNumListeners(listeners.getSource().getNumListeners());
        listenerMeasurement.setGaAccount(sp.getGaAccount());
        listenerMeasurement.setTitle(sp.getTitle());
        listenerMeasurement.setClientConnections(sp.getServerStats().getClientConnections());
        listenerMeasurement.setClients(sp.getServerStats().getClients());
        listenerMeasurement.setListenerConnections(sp.getServerStats().getListenerConnections());
        listenerMeasurement.setListenerConnections(sp.getServerStats().getListenerConnections());
        listenerMeasurement.setListeners(sp.getServerStats().getListeners());
        listenerMeasurement.setServerId(sp.getServerStats().getServerId());
        listenerMeasurement.setServerStart(sp.getServerStats().getServerStart());
        listenerMeasurement.setSources(sp.getServerStats().getSources());

        InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);
        influxDBMapper.save(listenerMeasurement);

        // Write points to InfluxDB.
/*        influxDB.write(Point.measurement("icecast_listeners")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("location", listeners.getSource().getMountPoint())
                .addField("server", sp.getServer().getStatsURL())
                .addField("num_listeners", listeners.getSource().getNumListeners())
                .build());
*/

    }

}



















/*


package com.coherentreceiver.analytics.task;

        import com.coherentreceiver.analytics.configuration.Configuration;
        import com.coherentreceiver.analytics.configuration.model.Server;
        import com.coherentreceiver.analytics.fetcher.model.icecastmodel.stats.StreamProperty;
        import org.influxdb.InfluxDB;
        import org.influxdb.InfluxDBFactory;
        import org.influxdb.dto.Pong;
        import org.influxdb.dto.Query;
        import org.slf4j.LoggerFactory;

        import java.util.List;

public class InitInfluxDBTask {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InitInfluxDBTask.class);
    protected String UNKNOWNTITLE = "NO_TITLE";

    public InitInfluxDBTask(Configuration configuration) {

        InfluxDB influxDB = InfluxDBFactory.connect(configuration.getInfluxdbDatabaseUrl(), configuration.getInfluxdbUserName(), configuration.getInfluxdbPassword());

        Pong response = influxDB.ping();
        if (response.getVersion().equalsIgnoreCase("unknown")) {
            logger.error("Error pinging influxDB server server.");
            return;
        }



        String databaseName = configuration.getInfluxdbDb();
        influxDB.query(new Query("CREATE DATABASE " + databaseName));
        influxDB.setDatabase(databaseName);

        String retentionPolicyName = "one_day_only";
        influxDB.query(new Query("CREATE RETENTION POLICY " + retentionPolicyName
                + " ON " + databaseName + " DURATION 1d REPLICATION 1 DEFAULT"));
        influxDB.setRetentionPolicy(retentionPolicyName);


        InfluxDBUpdateTask influxDBUpdateTask = new InfluxDBUpdateTask(influxDB);
        InitSynchronous initSynchronous = new InitSynchronous();

        configuration.getServers()
                .stream()
                .map(server -> initSynchronous.getStreamProperties(server))
                .forEach(streamProperty -> streamProperty.forEach(sp -> influxDBUpdateTask.updateSynchronous(sp)));

        influxDB.close();

    }

}




 */