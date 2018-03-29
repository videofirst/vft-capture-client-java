/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-present, Video First Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.videofirst.vft.capture.client.api.impl;

import static co.videofirst.vft.capture.client.api.impl.DefaultCaptureApi.HttpMethod.DELETE;
import static co.videofirst.vft.capture.client.api.impl.DefaultCaptureApi.HttpMethod.GET;
import static co.videofirst.vft.capture.client.api.impl.DefaultCaptureApi.HttpMethod.POST;

import co.videofirst.vft.capture.client.api.CaptureApi;
import co.videofirst.vft.capture.client.exception.VftCaptureException;
import co.videofirst.vft.capture.client.gson.LocalDateTimeTypeAdapter;
import co.videofirst.vft.capture.client.model.UploadStatus;
import co.videofirst.vft.capture.client.model.capture.Capture;
import co.videofirst.vft.capture.client.model.capture.CaptureFinishParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStartParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStatus;
import co.videofirst.vft.capture.client.model.capture.CaptureSummary;
import co.videofirst.vft.capture.client.model.info.Info;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Default implementation the CaptureApi interface.
 *
 * @author Bob Marks
 */
public class DefaultCaptureApi implements CaptureApi {

    // Injected fields

    private String captureApiUrl;
    private String captureApiUser;
    private String captureApiPass;

    // Other fields

    private String capturesUrl;
    private String uploadUrl;
    private Gson gson;

    public DefaultCaptureApi(String captureApiUrl, String captureApiUser,
        String captureApiPass) {
        this.captureApiUrl = captureApiUrl;
        this.captureApiUser = captureApiUser;
        this.captureApiPass = captureApiPass;

        this.capturesUrl = captureApiUrl + "/captures";
        this.uploadUrl = capturesUrl + "/uploads";
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();
    }

    @Override
    public Info info() {
        Info info = api(this.captureApiUrl, GET, Info.class);
        return info;
    }

    @Override
    public List<CaptureSummary> list() {
        List<CaptureSummary> list = api(capturesUrl, GET, new TypeToken<List<CaptureSummary>>() {
        }.getType());
        return list;
    }

    @Override
    public Capture select(String captureId) {
        Capture capture = api(capturesUrl + "/" + captureId, GET, Capture.class);
        return capture;
    }

    @Override
    public CaptureStatus start(CaptureStartParams captureStartParams) {
        CaptureStatus captureStatus = api(capturesUrl + "/start", POST, CaptureStatus.class,
            captureStartParams);
        return captureStatus;
    }

    @Override
    public CaptureStatus record() {
        CaptureStatus captureStatus = api(capturesUrl + "/record", POST, CaptureStatus.class);
        return captureStatus;
    }

    @Override
    public CaptureStatus stop() {
        CaptureStatus captureStatus = api(capturesUrl + "/stop", POST, CaptureStatus.class);
        return captureStatus;
    }

    @Override
    public CaptureStatus finish(CaptureFinishParams captureFinishParams) {
        CaptureStatus captureStatus = api(capturesUrl + "/finish", POST, CaptureStatus.class,
            captureFinishParams);
        return captureStatus;
    }

    @Override
    public CaptureStatus cancel() {
        CaptureStatus captureStatus = api(capturesUrl + "/cancel", POST, CaptureStatus.class);
        return captureStatus;
    }

    @Override
    public CaptureStatus status() {
        CaptureStatus captureStatus = api(capturesUrl + "/status", GET, CaptureStatus.class);
        return captureStatus;
    }

    @Override
    public void delete(String captureId) {
        api(capturesUrl + "/" + captureId, DELETE);
    }

    @Override
    public List<UploadStatus> upload(String captureId) {
        List<UploadStatus> list = api(uploadUrl, POST, new TypeToken<List<UploadStatus>>() {
        }.getType());
        return list;
    }

    @Override
    public List<UploadStatus> uploadStatus() {
        List<UploadStatus> list = api(uploadUrl, GET, new TypeToken<List<UploadStatus>>() {
        }.getType());
        return list;
    }

    enum HttpMethod {
        GET, POST, DELETE
    }

    // private methods

    private <T> T api(String url, HttpMethod method) {
        return api(url, method, null, null, null);
    }

    private <T> T api(String url, HttpMethod method, Class<T> klass) {
        return api(url, method, klass, null, null);
    }

    private <T> T api(String url, HttpMethod method, Type type) {
        return api(url, method, null, type, null);
    }

    private <T> T api(String url, HttpMethod method, Class<T> klass, Object params) {
        return api(url, method, klass, null, params);
    }

    private <T> T api(String url, HttpMethod method, Class<T> klass, Type type, Object params) {
        try {
            // Execute HTTP call
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                this.captureApiUser, this.captureApiPass);
            provider.setCredentials(AuthScope.ANY, credentials);

            CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider).build();
            CloseableHttpResponse response = null;
            if (method == GET) {
                HttpGet httpGet = new HttpGet(url);
                setHeaders(httpGet);
                response = client.execute(httpGet);
            } else if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                setHeaders(httpPost);
                if (params != null) {
                    String jsonBody = gson.toJson(params);
                    httpPost.setEntity(new StringEntity(jsonBody));
                    httpPost.setHeader("Accept", "application/json");
                }
                response = client.execute(httpPost);
            }

            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                String body = EntityUtils.toString(response.getEntity());

                // Check status and update accordingly
                if (statusCode == 200) {
                    if (klass != null) {
                        T obj = gson.fromJson(body, klass);
                        return obj;
                    } else if (type != null) {
                        T obj = gson.fromJson(body, type);
                        return obj;
                    }
                    return null;
                } else {
                    throw new VftCaptureException(
                        "Error access VFT Capture API, url = " + url + " - " + body);
                }
            }
        } catch (IOException ioEx) {
            throw new VftCaptureException("Error accessing VFT Capture API, url = " + url, ioEx);
        }
        return null;
    }

    private void setHeaders(HttpRequestBase httpRequest) {
        httpRequest.setHeader("Accept", "application/json");
        httpRequest.setHeader("Content-Type", "application/json");
    }

}
