
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

package com.coherentreceiver.analytics.fetcher.service;

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
