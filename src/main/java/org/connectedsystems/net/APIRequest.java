package org.connectedsystems.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Represents an API request to a server.
 */
public class APIRequest {
    protected HttpURLConnection connection;
    private String responseBody;
    private boolean didExecute = false;

    /**
     * Execute the API request and return the response as an APIResponse object.
     *
     * @param clazz The class type to deserialize the response into.
     *              This should be a class that matches the structure of the expected response,
     *              or {@link Void} if no response body is expected.
     * @param <T>   The type of the response data.
     * @return An APIResponse object containing the response data.
     * @throws IOException if an error occurs while making the API request or reading the response.
     */
    public <T> APIResponse<T> execute(Class<T> clazz) throws IOException {
        execute();
        return new APIResponse<>(clazz, this);
    }

    /**
     * Execute the API request and handle the response.
     *
     * @throws IOException if an error occurs while making the API request or reading the response.
     */
    public void execute() throws IOException {
        if (connection == null) {
            throw new IllegalStateException("Connection is not initialized");
        }
        connection.connect();
        int responseCode = connection.getResponseCode();

        if (responseCode >= 200 && responseCode < 400) {
            responseBody = readStream(connection.getInputStream());
        } else {
            String errorMessage = readStream(connection.getErrorStream());
            if (!errorMessage.isEmpty()) {
                System.err.println("Error response: " + errorMessage);
            }
        }

        connection.disconnect();
        didExecute = true;
    }

    /**
     * Read an InputStream and return its content as a String.
     *
     * @param inputStream The InputStream to read from.
     * @return The content of the InputStream as a String.
     * @throws IOException if an error occurs while reading the InputStream.
     */
    private String readStream(InputStream inputStream) throws IOException {
        if (inputStream == null) return "";

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    /**
     * Get the response body of the API request.
     *
     * @return The response body as a String.
     * @throws IllegalStateException if {@link #execute()} has not been called yet.
     */
    public String getResponseBody() {
        if (!didExecute) throw new IllegalStateException("Request has not been executed yet.");
        return responseBody;
    }

    /**
     * Get the HTTP connection used for the API request.
     * This can be used to access response headers and other connection details.
     *
     * @return The HttpURLConnection object.
     * @throws IllegalStateException if {@link #execute()} has not been called yet.
     */
    public HttpURLConnection getConnection() {
        if (!didExecute) throw new IllegalStateException("Request has not been executed yet.");
        return connection;
    }

    /**
     * Builder class for constructing APIRequest objects.
     */
    public static class APIRequestBuilder {
        private final APIRequest apiRequest;
        private String apiRoot;
        private Endpoint resourcePath;
        private String resourceId;
        private Endpoint subResourcePath;
        private String subResourceId;
        private Map<String, String> queryParams;
        private String body;
        private Map<String, String> headers;
        private String authorizationToken;
        private HttpRequestMethod requestMethod;

        public APIRequestBuilder() {
            apiRequest = new APIRequest();
        }

        /**
         * Set the root URL for the API request.
         * Should not include the query string or resource path.
         *
         * @param apiRoot The root URL to use for the API request.
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setApiRoot(String apiRoot) {
            this.apiRoot = apiRoot;
            return this;
        }

        public APIRequestBuilder setResourcePath(Endpoint resourcePath) {
            this.resourcePath = resourcePath;
            return this;
        }

        public APIRequestBuilder setResourceId(String resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public APIRequestBuilder setSubResourcePath(Endpoint subResourcePath) {
            this.subResourcePath = subResourcePath;
            return this;
        }

        public APIRequestBuilder setSubResourceId(String subResourceId) {
            this.subResourceId = subResourceId;
            return this;
        }

        /**
         * Set the query parameters for the API request.
         * These will be appended to the URL as a query string.
         *
         * @param params A map of query parameters to include in the request.
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setQueryParams(Map<String, String> params) {
            this.queryParams = params;
            return this;
        }

        /**
         * Set the body of the API request.
         * This is typically used for POST or PUT requests.
         *
         * @param body The body content to include in the request.
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        /**
         * Set additional headers for the API request.
         *
         * @param headers A map of headers to include in the request.
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        /**
         * Set the authorization token for the API request.
         * This is typically a Base64-encoded string of the username and password.
         *
         * @param authorizationToken The authorization token to include in the request.
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setAuthorizationToken(String authorizationToken) {
            this.authorizationToken = authorizationToken;
            return this;
        }

        /**
         * Set the HTTP request method for the API request.
         *
         * @param requestMethod The HTTP method to use for the request (e.g., GET, POST, PUT, DELETE).
         * @return The APIRequestBuilder instance for method chaining.
         */
        public APIRequestBuilder setRequestMethod(HttpRequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        /**
         * Build the APIRequest object with the specified parameters.
         *
         * @return The constructed APIRequest object.
         * @throws IOException              if an error occurs while building the request.
         * @throws IllegalArgumentException if any required parameters are missing or invalid.
         */
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
            appendResources(urlWithParams);
            appendQueryString(urlWithParams);

            URL url = new URL(urlWithParams.toString());
            apiRequest.connection = (HttpURLConnection) url.openConnection();
            apiRequest.connection.setRequestMethod(requestMethod.name());
            apiRequest.connection.setRequestProperty("Content-Type", "application/json");

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

        private void appendResources(StringBuilder stringBuilder) {
            if (resourcePath != null) {
                stringBuilder.append("/").append(resourcePath.getPath());
                if (resourceId != null && !resourceId.isEmpty()) {
                    stringBuilder.append("/").append(resourceId);
                    if (subResourcePath != null) {
                        stringBuilder.append("/").append(subResourcePath.getPath());
                        if (subResourceId != null && !subResourceId.isEmpty()) {
                            stringBuilder.append("/").append(subResourceId);
                        }
                    }
                }
            }
        }

        private void appendQueryString(StringBuilder stringBuilder) {
            if (queryParams != null && !queryParams.isEmpty()) {
                stringBuilder.append("?");
                queryParams.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("&"));
                stringBuilder.deleteCharAt(stringBuilder.length() - 1); // Remove the last "&"
            }
        }
    }
}