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

package com.coherentreceiver.analytics.icecast.parser;

import com.coherentreceiver.analytics.icecast.fetcher.model.icecast.listclients.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;


/**
 * parser of the HTML icecast/shoutcast standard form
 */
public class IcecastHTTPParser implements Parser {

    private String roundbox;
    private String mounthead;
    private String mountcont;

    public IcecastHTTPParser(String streamType) {

        switch (streamType) {
            case "mounthead": {
                roundbox = "roundbox";
                mounthead = "mounthead";
                mountcont = "mountcont";
                break;

            }
            case "roundtop": {
                roundbox = "roundcont";
                mounthead = "roundtop";
                mountcont = "newscontent";
                break;
            }


        }

    }

        public List<Stream> parse(URI uri) throws ParseException
        {
            List<Stream> streams = new LinkedList<Stream>();

            try{

                Document doc = Jsoup.connect(uri.toString()).get();


                String title=doc.title();

                Elements roundboxes = doc.getElementsByClass (roundbox);

                for (Element roundbox: roundboxes){

                    Elements tdr=null;

                    Stream stream = new Stream();


                    Elements mountHead = roundbox.getElementsByClass(mounthead);

                    String mountPoint = mountHead.first().getElementsByClass("mount").text();
                    stream.setMountPoint(mountPoint);

                    Elements mountcontent = roundbox.getElementsByClass(mountcont);

                    if (mountcontent.hasClass(mountcont)) {
                        Element child = mountcontent.first();
                        tdr=child.getElementsByTag("tr");

                    };

                    try {
                        if (tdr.size() == 0) {
                            continue;
                        }
                    }catch (Exception e){
                        System.out.println (e);
                        continue;
                    }


                    streams.add (parseParameter(tdr));

                }


            }catch (Exception e){
                System.out.println(e);
                throw new ParseException("Error in parse", e);
            }

            return streams;
        }



    public Stream parseParameter (Elements tdr){

    Stream stream = new Stream();

    for (int i=0; i<tdr.size();i++) {

        String name = tdr.get(i).child(0).text();
        String value = tdr.get(i).child(1).text();

        if (name.startsWith("Mount Point")){
            stream.setMountPoint(name.substring(12,name.length()));

        } else if (name.equalsIgnoreCase("RadioStream Name:")
                || name.equalsIgnoreCase("Stream Name:")
                || name.equalsIgnoreCase("Stream Title:")) {
            stream.setTitle(value);
        } else if (name.equalsIgnoreCase("RadioStream Description:")
                || name.equalsIgnoreCase("Stream Description:")) {
            stream.setDescription(value);
        } else if (name.equalsIgnoreCase("Content Type:")) {
            stream.setContentType(value);
        } else if (name.equalsIgnoreCase("Bitrate:")) {
            stream.setBitRate(value);
        } else if (name.equalsIgnoreCase("Listeners (current):")
                || name.equalsIgnoreCase("Current Listeners:")) {
            try {
                stream.setCurrentListenerCount(Integer.parseInt(value));
            } catch (NumberFormatException e) {
            }
        } else if (name.equalsIgnoreCase("Listeners (peak):")
                || name.equalsIgnoreCase("Peak Listeners:")) {
            try {
                stream.setPeakListenerCount(Integer.parseInt(value));
            } catch (NumberFormatException e) {
            }
        } else if (name.equalsIgnoreCase("Genre:")
                || name.equalsIgnoreCase("Stream Genre:") ) {
            stream.setGenre(value);
        } else if (name.equalsIgnoreCase("Currently playing:")
                || name.equalsIgnoreCase("Current Song:") ) {
            stream.setCurrentSong(value);
        } else if (name.equalsIgnoreCase("RadioStream URL:")
                || name.equalsIgnoreCase("Stream URL:")) {
            stream.setUri(value);
        }
    }
        return stream;

    }


    public static void main (String[] args){
        System.out.println ("TEST");
        IcecastHTTPParser icecastHTTPParser = new IcecastHTTPParser("mounthead");
    }


}

















