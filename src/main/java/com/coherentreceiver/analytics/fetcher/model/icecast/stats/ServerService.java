package com.coherentreceiver.analytics.fetcher.model.icecast.stats;

import com.coherentreceiver.analytics.configuration.model.MountpointsGAAccount;
import com.coherentreceiver.analytics.configuration.model.Server;
import com.coherentreceiver.analytics.fetcher.service.ServerStatsFetcher;
import com.coherentreceiver.analytics.helper.decoding.DecoderFactory;
import com.coherentreceiver.analytics.helper.idgenerator.IDGeneratorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServerService {

    final Logger logger = LoggerFactory.getLogger(Server.class);

    protected String UNKNOWNTITLE = "NO_TITLE";

    @Autowired
    ServerStatsFetcher serverStatsFetcher;

    public List<StreamProperty> getStreamPropertiesByServer (Server server) {

        List<StreamProperty> streamProperties = new ArrayList<>();

        try {

            List<MountpointsGAAccount> mountpointsGAAccounts = server.getMountpointsGAAccounts();

            //get server stats from the server
            ServerStats serverStats = serverStatsFetcher.getServerStats(server.getStatsURL(), server.getLogin(), server.getPassword());

            //StreamProperty contains information about the stream, title, gaacccount and number of listeners


            for (RadioStream radioStream : serverStats.getRadioStreams()) {

                //get the assignment between mountpoints and gaaccounts on this server
                String mountPoint = radioStream.getMountPoint(); //get current mountpoint
                Optional<MountpointsGAAccount> gaAccount = null; //gaAccount
                gaAccount = mountpointsGAAccounts.stream()
                        .filter(d -> d.getMountPoint().compareTo(mountPoint) == 0)
                        .findFirst();

                if (gaAccount.isPresent()) {
                    MountpointsGAAccount mountpointGAAccount = gaAccount.get();
                    StreamProperty streamProperty = new StreamProperty();
                    streamProperty.setGaAccount(mountpointGAAccount.getGaAccount());
                    if (radioStream.getTitle() != null) {
                        streamProperty.setTitle(radioStream.getTitle());
                    } else streamProperty.setTitle(UNKNOWNTITLE);

                    streamProperty.setMountPoint(radioStream.getMountPoint());
                    streamProperty.setServer(server);
                    streamProperty.setServerStats(serverStats);
                    streamProperty.setIdGenerator(IDGeneratorFactory.getIDGenerator(mountpointGAAccount.getUserIdAlgorithm()));
                    streamProperty.setCharacterDecoder(DecoderFactory.getDecoder(mountpointGAAccount.getCharacterDecoding()));

                    streamProperty.setTimeStamp(LocalDateTime.now());

                    streamProperties.add(streamProperty);

                } else {
                    continue;
                }


            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);

        }

        return streamProperties;

    }
}