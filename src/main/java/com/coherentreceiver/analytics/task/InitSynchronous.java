/*     Copyright 2017 SWI Kommunikations- und Computer GmbH
*
*        Licensed under the Apache License, Version 2.0 (the "License");
*        you may not use this file except in compliance with the License.
*        You may obtain a copy of the License at
*
*        http://www.apache.org/licenses/LICENSE-2.0
*
*        Unless required by applicable law or agreed to in writing, software
*       distributed under the License is distributed on an "AS IS" BASIS,
*        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*        See the License for the specific language governing permissions and
*        limitations under the License.
*/

package com.coherentreceiver.analytics.task;

import com.coherentreceiver.analytics.configuration.Configuration;
import com.coherentreceiver.analytics.configuration.MountpointsGAAccount;
import com.coherentreceiver.analytics.configuration.Server;
import com.coherentreceiver.analytics.controller.TaskType;
import com.coherentreceiver.analytics.fetcher.ListenersFetcher;
import com.coherentreceiver.analytics.fetcher.ServerStatsFetcher;
import com.coherentreceiver.analytics.helper.decoding.DecoderFactory;
import com.coherentreceiver.analytics.helper.idgenerator.IDGeneratorFactory;
import com.coherentreceiver.analytics.icecastmodel.listclients.Listeners;
import com.coherentreceiver.analytics.icecastmodel.stats.RadioStream;
import com.coherentreceiver.analytics.icecastmodel.stats.ServerStats;
import com.coherentreceiver.analytics.icecastmodel.stats.StreamProperty;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class InitSynchronous {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InitSynchronous.class);
    protected String UNKNOWNTITLE = "NO_TITLE";

    public InitSynchronous (Configuration configuration) {

        GAUpdateTask gaUpdateTask = new GAUpdateTask();

        List<Server> servers = configuration.getServers();

        for (Server server : servers) {

            List<StreamProperty> streamProperties = getStreamProperties(server);

            for (StreamProperty streamProperty : streamProperties) {


                gaUpdateTask.updateSynchronous(streamProperty);


            }


        }

    }

        public List<StreamProperty> getStreamProperties (Server server) {

            List<StreamProperty> streamProperties = new ArrayList<>();

            try {

                List<MountpointsGAAccount> mountpointsGAAccounts = server.getMountpointsGAAccounts();
                ServerStatsFetcher serverStatsFetcher = new ServerStatsFetcher();

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
                        if (radioStream.getTitle() != null){
                            streamProperty.setTitle(radioStream.getTitle());}
                                else streamProperty.setTitle(UNKNOWNTITLE);

                        streamProperty.setMountPoint(radioStream.getMountPoint());
                        streamProperty.setServer(server);
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
