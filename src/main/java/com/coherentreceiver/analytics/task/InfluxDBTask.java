package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.mediaserver.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.platform.streaminganalytics.model.ListenersMeasurement;
import com.coherentreceiver.analytics.platform.streaminganalytics.model.SingleListenerMeasurement;
import com.coherentreceiver.analytics.platform.streaminganalytics.service.ListenersMeasurementService;
import com.coherentreceiver.analytics.platform.streaminganalytics.service.SingleListenerMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InfluxDBTask extends AbstractTask  {

    final Logger logger = LoggerFactory.getLogger(InfluxDBTask.class);

    @Autowired
    ListenersMeasurementService<ListenersMeasurement, Instant> listenerMeasurementService;

    @Autowired
    SingleListenerMeasurementService <SingleListenerMeasurement, Instant> singleListenerMeasurementService;


  //  @Scheduled (fixedDelayString = "#{appconfiguration.influxDBUpdateFrequency*1000}")
    public void setInfluxDBUpdateTask () {

        logger.info ("start influxdb process");
        super.updateTask();
        logger.info("finish influxdb process");
    }

    public void updateSynchronous (StreamProperty sp, Listeners listeners){

        listenerMeasurementService.save(sp, listeners);
        singleListenerMeasurementService.saveAll(sp, listeners);

        }

}






















/*


package com.coherentreceiver.analytics.task;

        import com.coherentreceiver.analytics.configuration.Configuration;
        import com.coherentreceiver.analytics.configuration.model.Server;
        import com.coherentreceiver.analytics.icecast.fetcher.model.icecastmodel.stats.StreamProperty;
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