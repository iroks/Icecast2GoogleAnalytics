/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.proxy.configuration;

import com.coherentreceiver.analytics.appconfiguration.model.Config;
import com.coherentreceiver.analytics.appconfiguration.model.Server;
import com.coherentreceiver.analytics.platform.streaminganalytics.service.SingleListenerMeasurementService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class IcecastAdminPreZuulFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Config config;

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {


            HttpServletRequest request = ctx.getRequest();

            logger.info("Zuul pre-filter; Requested URL is " + request.getRequestURI());

            Map<String, String[]> paramMap = request.getParameterMap();

            String sID = "";

            if (paramMap.size() > 0 && paramMap.get("server").length > 0) {
                sID = Arrays.stream(paramMap.get("server")).findAny().get();
            }
            Integer reqServerId = Integer.decode(sID);


            Server server = config.getServers()
                    .stream()
                    .filter(s -> s.getServerId() == reqServerId)
                    .findAny().get();

            String auth = server.getLogin() + ":" + server.getPassword();
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);
            ctx.addZuulRequestHeader("Authorization", authHeader);


            Map<String, List<String>> params = request.getParameterMap()
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                         e -> Stream.of(e.getValue()).collect(Collectors.toList())
                         ));

            logger.info("Parametres : {}", params
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + "=" + Stream.of(e.getValue()).collect(Collectors.toList()))
                    .collect(Collectors.toList()));

            ctx.setRequestQueryParams(params);
            ctx.setRouteHost(new URL(server.getBaseURL()));

            //todo: forward to REST
        } catch (NoSuchElementException noSuchElementException) {
            logger.info("server not found ");
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseBody("Server not found");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        } catch (NullPointerException nullPointerException) {
            logger.info("no server parameter found in request");
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseBody("No server parameter found in request");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }


    @Override
    public boolean shouldFilter() {
        String endpointToFilter = RequestContext.getCurrentContext().getRequest().getRequestURI();
        logger.info(endpointToFilter);
        return endpointToFilter.contains("admin");
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public String filterType() {
        return "pre";
    }

}