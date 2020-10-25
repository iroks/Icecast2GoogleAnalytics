/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.platform.streaminganalytics.controller;

import com.coherentreceiver.analytics.geo.GeoProviderService;
import com.coherentreceiver.analytics.platform.streaminganalytics.dao.InfluxDBRepository;
import com.coherentreceiver.analytics.platform.streaminganalytics.model.SingleListenerMeasurement;
import com.coherentreceiver.analytics.platform.streaminganalytics.service.SingleListenerMeasurementService;
import com.maxmind.geoip2.GeoIp2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import java.time.Instant;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TrackingPixelController {

    private final SingleListenerMeasurementService singleListenerMeasurementService;

    private GeoProviderService geoProviderService;

    private InfluxDBRepository<SingleListenerMeasurement, Instant> influxDBRepository;

    // https://stackoverflow.com/questions/4548769/dynamically-generate-transparent-tracking-pixel
    static byte[] trackingGif = { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61, 0x1, 0x0, 0x1, 0x0, (byte) 0x80, 0x0, 0x0, (byte)  0xff, (byte)  0xff,  (byte) 0xff, 0x0, 0x0, 0x0, 0x2c, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0, 0x0, 0x2, 0x2, 0x44, 0x1, 0x0, 0x3b };
    static byte[] trackingPng = {(byte)0x89,0x50,0x4E,0x47,0x0D,0x0A,0x1A,0x0A,0x00,0x00,0x00,0x0D,0x49,0x48,0x44,0x52,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x01,0x08,0x06,0x00,0x00,0x00,0x1F,0x15,(byte)0xC4,(byte)0x89,0x00,0x00,0x00,0x0B,0x49,0x44,0x41,0x54,0x78,(byte)0xDA,0x63,0x60,0x00,0x02,0x00,0x00,0x05,0x00,0x01,(byte)0xE9,(byte)0xFA,(byte)0xDC,(byte)0xD8,0x00,0x00,0x00,0x00,0x49,0x45,0x4E,0x44,(byte)0xAE,0x42,0x60,(byte)0x82};

    TrackingPixelController (SingleListenerMeasurementService singleListenerMeasurementService
                        , InfluxDBRepository<SingleListenerMeasurement, Instant> influxDBRepository
                        , GeoProviderService geoProviderService) {
        this.singleListenerMeasurementService = singleListenerMeasurementService;
        this.influxDBRepository = influxDBRepository;
        this.geoProviderService = geoProviderService;
    }

    @RequestMapping(
            value = "/pixel",
            method = GET,
            produces = "image/png"
    )
    public byte[] getPixel (
            HttpServletRequest request,
            @RequestHeader(value="User-Agent") String userAgent,
            @RequestHeader(value = "referer") String referer,
            @RequestParam(value = "connected", defaultValue="0") int connectedTime,
            @RequestParam(value = "id", defaultValue="0") String id,
            @RequestParam (value = "title", defaultValue = "") String title,
            @RequestParam (value = "mountpoint", defaultValue = "") String mountpoint,
            @RequestParam (value = "gaaccount", defaultValue = "") String gaaccount
    ){

        SingleListenerMeasurement singleListenerMeasurement = new SingleListenerMeasurement();

        singleListenerMeasurement.setTime(Instant.now());
        singleListenerMeasurement.setReferer(Optional.ofNullable(referer).orElse(""));
        singleListenerMeasurement.setUserAgent( Optional.ofNullable(userAgent).orElse(""));

        singleListenerMeasurement.setConnected(connectedTime);
        singleListenerMeasurement.setId(id);

        String remoteIP = Optional.ofNullable(request.getRemoteAddr()).orElse ("0.0.0.0");
        singleListenerMeasurement.setIp (remoteIP);
        singleListenerMeasurement.setCountry( Optional.ofNullable(geoProviderService.getCountry(remoteIP)).orElse(""));
        singleListenerMeasurement.setCity( Optional.ofNullable(geoProviderService.getCity(remoteIP)).orElse(""));

        singleListenerMeasurement.setTitle(title);
        singleListenerMeasurement.setMountpoint(mountpoint);
        singleListenerMeasurement.setGaAccount(gaaccount);

        singleListenerMeasurementService.save(singleListenerMeasurement);

        return trackingPng;
    }


}
