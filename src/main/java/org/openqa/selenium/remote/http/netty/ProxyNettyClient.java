/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openqa.selenium.remote.http.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.remote.http.*;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * This code was copied from https://docs.experitest.com/display/TE/Appium+Selenium+Proxy
 * Based on this code with disabled proxySelector: .setUseProxySelector(false)
 * https://github.com/SeleniumHQ/selenium/blob/selenium-4.3.0/java/src/org/openqa/selenium/remote/http/netty/NettyClient.java
 */
public class ProxyNettyClient implements HttpClient {

    private static final Timer TIMER;
    private static final AsyncHttpClient client = createHttpClient(ClientConfig.defaultConfig());

    static {
        ThreadFactory threadFactory = new DefaultThreadFactory("netty-client-timer", true);
        TIMER = new HashedWheelTimer(
                threadFactory,
                AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration(),
                TimeUnit.MILLISECONDS,
                AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize());
    }

    private final ClientConfig config;
    private final HttpHandler handler;
    private final BiFunction<HttpRequest, WebSocket.Listener, WebSocket> toWebSocket;

    private ProxyNettyClient(ClientConfig config) {
        this.config = Require.nonNull("HTTP client config", config);
        this.handler = new NettyHttpHandler(config, client).with(config.filter());
        this.toWebSocket = NettyWebSocket.create(config, client);
    }

    /**
     * Converts a long to an int, clamping the maximum and minimum values to
     * fit in an integer without overflowing.
     */
    static int toClampedInt(long value) {
        return (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, value));
    }

    private static AsyncHttpClient createHttpClient(ClientConfig config) {
        DefaultAsyncHttpClientConfig.Builder builder =
                new DefaultAsyncHttpClientConfig.Builder()
                        .setThreadFactory(new DefaultThreadFactory("AsyncHttpClient", true))
                        .setUseInsecureTrustManager(true)
                        .setAggregateWebSocketFrameFragments(true)
                        .setWebSocketMaxBufferSize(Integer.MAX_VALUE)
                        .setWebSocketMaxFrameSize(Integer.MAX_VALUE)
                        .setNettyTimer(TIMER)
                        .setRequestTimeout(toClampedInt(config.readTimeout().toMillis()))
                        .setConnectTimeout(toClampedInt(config.connectionTimeout().toMillis()))
                        .setReadTimeout(toClampedInt(config.readTimeout().toMillis()))
                        .setFollowRedirect(true)
                        .setUseProxyProperties(true)
                        .setUseProxySelector(false)
                        .setMaxRequestRetry(0);

        return Dsl.asyncHttpClient(builder);
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        request.addHeader("My-Extra-Header", "1111-2222-3333-4444-5555");
        return handler.execute(request);
    }

    @Override
    public WebSocket openSocket(HttpRequest request, WebSocket.Listener listener) {
        Require.nonNull("Request to send", request);
        Require.nonNull("WebSocket listener", listener);

        return toWebSocket.apply(request, listener);
    }

    @Override
    public HttpClient with(Filter filter) {
        Require.nonNull("Filter", filter);
        return new ProxyNettyClient(config.withFilter(filter));
    }

    @Override
    public void close() {
        // no-op -- closing the client when the JVM shuts down
    }

    @HttpClientName("netty")
    public static class Factory implements HttpClient.Factory {

        @Override
        public HttpClient createClient(ClientConfig config) {
            Require.nonNull("Client config", config);

            if (config.baseUri() != null && "unix".equals(config.baseUri().getScheme())) {
                return new NettyDomainSocketClient(config);
            }

            return new ProxyNettyClient(config);
        }
    }
}