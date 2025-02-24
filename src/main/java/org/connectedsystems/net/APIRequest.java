package org.connectedsystems.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIRequest {
    protected HttpURLConnection connection;
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public <T> APIResponse<T> execute(Class<T> clazz, boolean isSingleItem) throws IOException {
        execute();
        return new APIResponse<>(this, clazz, isSingleItem);
    }

    public void execute() throws IOException {
        if (connection == null) {
            throw new IllegalStateException("Connection is not initialized");
        }
        connection.connect();
        responseCode = connection.getResponseCode();
        responseMessage = connection.getResponseMessage();

        if (responseCode >= 200 && responseCode < 300) {
            responseBody = readResponse();
        }

        connection.disconnect();
    }

    private String readResponse() throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public static class APIRequestBuilder {
        private final APIRequest apiRequest;
        private String apiRoot;
        private Map<String, String> params;
        private String body;
        private Map<String, String> headers;
        private String authorizationToken;
        private HttpRequestMethod requestMethod;

        public APIRequestBuilder() {
            apiRequest = new APIRequest();
        }

        public APIRequestBuilder setApiRoot(String apiRoot) {
            this.apiRoot = apiRoot;
            return this;
        }

        public APIRequestBuilder setParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public APIRequestBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public APIRequestBuilder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public APIRequestBuilder setAuthorizationToken(String authorizationToken) {
            this.authorizationToken = authorizationToken;
            return this;
        }

        public APIRequestBuilder setRequestMethod(HttpRequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public APIRequest build() throws IOException {
            if (apiRoot == null || apiRoot.isEmpty()) {
                throw new IllegalArgumentException("API root cannot be null or empty");
            }

            if (requestMethod == null) {
                throw new IllegalArgumentException("Request method cannot be null");
            }

            if ((requestMethod == HttpRequestMethod.POST || requestMethod == HttpRequestMethod.PUT) && body == null) {
                throw new IllegalArgumentException("Body cannot be null for POST or PUT requests");
            }

            StringBuilder urlWithParams = new StringBuilder(apiRoot);
            if (params != null && !params.isEmpty()) {
                urlWithParams.append("?");
                params.forEach((key, value) -> urlWithParams.append(key).append("=").append(value).append("&"));
                urlWithParams.deleteCharAt(urlWithParams.length() - 1); // Remove the last "&"
            }

            System.out.println("APIRequest.build URL: " + urlWithParams);

            URL url = new URL(urlWithParams.toString());
            apiRequest.connection = (HttpURLConnection) url.openConnection();
            apiRequest.connection.setRequestMethod(requestMethod.name());

            if (headers != null && !headers.isEmpty()) {
                headers.forEach(apiRequest.connection::setRequestProperty);
            }

            if (authorizationToken != null && !authorizationToken.isEmpty()) {
                apiRequest.connection.setRequestProperty("Authorization", "Basic " + authorizationToken);
            }

            if (requestMethod == HttpRequestMethod.POST || requestMethod == HttpRequestMethod.PUT) {
                apiRequest.connection.setDoOutput(true);
                apiRequest.connection.getOutputStream().write(body.getBytes());
            }

            return apiRequest;
        }
    }
}