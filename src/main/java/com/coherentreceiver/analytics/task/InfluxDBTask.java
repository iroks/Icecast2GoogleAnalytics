package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.configuration.model.Config;
import com.coherentreceiver.analytics.configuration.service.Configurator;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.ServerService;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamPropertyService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class InfluxDBTask  {

    final Logger logger = LoggerFactory.getLogger(InfluxDBTask.class);

    @Autowired
    InfluxDB influxDB;

    @Resource(name="appconfig")
    private Config config;

    @Autowired
    private StreamPropertyService streamPropertyService;

    @Autowired
    private ServerService serverService;

    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedDelay = 40000)
    public void request () {

        config.getServers()
                .stream()
                .map(server -> serverService.getStreamPropertiesByServer(server))
                .forEach(streamProperty -> streamProperty.forEach(sp -> updateSynchronous(sp)));

        logger.debug("influxdb process done");
    }

    public void updateSynchronous (StreamProperty sp){

        streamPropertyService.setStreamProperty(sp);
        Listeners listeners = streamPropertyService.getListeners ();

        //there are no listeners anymore
        if (listeners==null) {
            return;
        }

        // Write points to InfluxDB.
        influxDB.write(Point.measurement("icecast_listeners")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("location", listeners.getSource().getMountPoint())
                .addField("server", sp.getServer().getStatsURL())
                .addField("num_listeners", listeners.getSource().getNumListeners())
                .build());


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