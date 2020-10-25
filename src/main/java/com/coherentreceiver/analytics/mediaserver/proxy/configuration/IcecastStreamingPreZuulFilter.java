/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.proxy.configuration;

import com.coherentreceiver.analytics.geo.GeoProviderService;
import com.coherentreceiver.analytics.platform.streaminganalytics.model.SingleListenerMeasurement;
import com.coherentreceiver.analytics.platform.streaminganalytics.service.SingleListenerMeasurementService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.time.Instant;
import java.util.Optional;


@Component
public class IcecastStreamingPreZuulFilter extends ZuulFilter {

    private final Logger logger =  LoggerFactory.getLogger(getClass());

    @Autowired
    GeoProviderService geoProviderService;

    @Autowired
    SingleListenerMeasurementService singleListenerMeasurementService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String endpointToFilter = RequestContext.getCurrentContext().getRequest().getRequestURI();
        logger.info("requested end point" + endpointToFilter);
        if (endpointToFilter.contains ("admin")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object run() throws ZuulException {

        //todo: refactor with trackingpixelcontroller necessary
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = ctx.getRequest();
        SingleListenerMeasurement singleListenerMeasurement = new SingleListenerMeasurement();

        singleListenerMeasurement.setTime(Instant.now());
        singleListenerMeasurement.setReferer(Optional.ofNullable(servletRequest.getHeader("referer")).orElse(""));
        singleListenerMeasurement.setUserAgent( Optional.ofNullable( servletRequest.getHeader( "User-Agent")).orElse(""));

        singleListenerMeasurement.setConnected(0);
        singleListenerMeasurement.setId("");

        String remoteIP = Optional.ofNullable(servletRequest.getRemoteAddr()).orElse ("0.0.0.0");
        singleListenerMeasurement.setIp (remoteIP);
        singleListenerMeasurement.setCountry( Optional.ofNullable(geoProviderService.getCountry(remoteIP)).orElse(""));
        singleListenerMeasurement.setCity( Optional.ofNullable(geoProviderService.getCity(remoteIP)).orElse(""));

        singleListenerMeasurement.setTitle("");
        singleListenerMeasurement.setMountpoint(ctx.getRequest().getRequestURI());
        singleListenerMeasurement.setGaAccount("");

        singleListenerMeasurementService.save(singleListenerMeasurement);

        return null;
    }
}
