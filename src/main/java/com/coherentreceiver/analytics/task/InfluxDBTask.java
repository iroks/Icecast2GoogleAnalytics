package com.coherentreceiver.analytics.task;

import com.brsanthu.googleanalytics.PageViewHit;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.Listeners;
import com.coherentreceiver.analytics.fetcher.model.icecast.listclients.SingleListenerElement;
import com.coherentreceiver.analytics.fetcher.model.icecast.stats.StreamProperty;
import com.coherentreceiver.analytics.influxdb.ListenersMeasurement;
import com.coherentreceiver.analytics.influxdb.SingleListenerMeasurement;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.GeoIp2Provider;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.CityResponse;
import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Component
public class InfluxDBTask extends AbstractTask  {

    final Logger logger = LoggerFactory.getLogger(InfluxDBTask.class);

    @Autowired
    InfluxDB influxDB;

    @Autowired
    @Qualifier ("geoprovider")
    GeoIp2Provider geoProvider;


    @Scheduled (fixedDelayString = "#{appconfiguration.influxDBUpdateFrequency*1000}")
    public void setInfluxDBUpdateTask () {

        logger.info ("start influxdb process");
        super.updateTask();
        logger.info("finish influxdb process");
    }

    public void updateSynchronous (StreamProperty sp, Listeners listeners){

        InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);

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

        influxDBMapper.save(listenersMeasurement);

        for (SingleListenerElement singleListener : listeners.getSource().getListeners()) {
            SingleListenerMeasurement slMeasurement = new SingleListenerMeasurement(singleListener);

            CityResponse response=null;
            try {
                if (geoProvider != null){
                    InetAddress ip = InetAddress.getByName(slMeasurement.getIp());
                    response = geoProvider.city(ip);
                    }
            } catch (Exception ex){
                logger.info("IP Address not found in the database or Unknown host");}

                try {
                    slMeasurement.setCountry(Optional.ofNullable(response.getCountry().getName()).orElse(""));
                } catch (Exception ex) {
                    slMeasurement.setCountry ("");
                }

                try {
                    slMeasurement.setCity(Optional.ofNullable(response.getCity().getName()).orElse(""));
                } catch (Exception ex) {
                    slMeasurement.setCity("");
                }

                try{
                slMeasurement.setTitle(Optional.ofNullable(sp.getCharacterDecoder().decode(sp.getTitle())).orElse(""));
                slMeasurement.setMountpoint(Optional.ofNullable((sp.getMountPoint())).orElse(""));
                slMeasurement.setServerHost(Optional.ofNullable(sp.getServerStats().getHost()).orElse(""));
                slMeasurement.setGaAccount(Optional.ofNullable(sp.getGaAccount()).orElse(""));

                influxDBMapper.save(slMeasurement);

            } catch (Exception ex){
                ex.printStackTrace();
            }


        }


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