package com.coherentreceiver.analytics.helper.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class XMLConverter {
    private static final Logger log = LoggerFactory.getLogger(XMLConverter.class);

    public static  <E> E readConfigurationFromFile (String fileName, Class<E> clazz) throws Exception{

        E conf = null;
        String xml="";
        try {
            File configurationFile = new File(fileName);
            xml = inputStreamToString(new FileInputStream(configurationFile));
        } catch (Exception ex){
            log.info("could not open configuration file " + fileName +  " using absolute path; try relative path");
        }

        try {
            Resource resource = new ClassPathResource(fileName);
            xml = inputStreamToString(resource.getInputStream());
            }catch (Exception e){
                log.info ("could not open configuration file " + fileName + " using relative path");
            }

        return readConfigurationFromString(xml, clazz);

    }

    public static <E> E readConfigurationFromString (String xmlstring, Class<E> clazz) throws Exception{
        XmlMapper xmlMapper = new XmlMapper();
        E conf = (E) xmlMapper.readValue (xmlstring, clazz);
        return conf;
    }

    public static <E> String writeConfigurationToString (E e) throws Exception {

        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(e);
        return xml;
    }

    public static <E> void writeConfigurationToFile (String fileName, E e) throws Exception {
        String xmlConfiguration = writeConfigurationToString(e);
        File configurationFile = new File (fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(xmlConfiguration);
        writer.close();

    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
