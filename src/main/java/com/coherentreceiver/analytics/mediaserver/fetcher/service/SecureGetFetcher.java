/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.fetcher.service;

/**
 *
 */
        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.auth.UsernamePasswordCredentials;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.auth.BasicScheme;
        import org.apache.http.impl.client.DefaultHttpClient;

        import java.io.IOException;
        import java.io.InputStream;

/**
 *
 */
public class SecureGetFetcher implements HTTPFetcher {

    public InputStream fetch (String URL, String login, String password){

        //todo: simple copy paste from older project; refactoring is needed
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(login, password),
                "UTF-8", false));

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();

        try {
            return responseEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
