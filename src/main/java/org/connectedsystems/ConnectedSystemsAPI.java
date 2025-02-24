package org.connectedsystems;

import java.util.Base64;

public class ConnectedSystemsAPI {
    private final String apiRoot;
    private final String authorizationToken;
    private final SystemsAPI systemsAPI;

    public ConnectedSystemsAPI(String apiRoot, String authenticationToken) {
        this.apiRoot = apiRoot;
        this.authorizationToken = authenticationToken;
        this.systemsAPI = new SystemsAPI(this.apiRoot, this.authorizationToken);
    }

    public ConnectedSystemsAPI(String apiRoot, String username, String password) {
        this(apiRoot, Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
    }

    public String getApiRoot() {
        return apiRoot;
    }

    public SystemsAPI getSystemsAPI() {
        return systemsAPI;
    }
}