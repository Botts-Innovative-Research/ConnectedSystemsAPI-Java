package org.connectedsystems;

import org.connectedsystems.datamodels.SystemResource;
import org.connectedsystems.net.APIRequest;
import org.connectedsystems.net.APIResponse;
import org.connectedsystems.net.HttpRequestMethod;

import java.io.IOException;

public class SystemsAPI {
    private final String apiRoot;
    private final String authorizationToken;

    public SystemsAPI(String apiRoot, String authorizationToken) {
        this.apiRoot = apiRoot;
        this.authorizationToken = authorizationToken;
    }

    public APIResponse<SystemResource> getSystems() throws IOException {
        APIRequest apiRequest = new APIRequest.APIRequestBuilder()
                .setApiRoot(apiRoot + "/systems")
                .setAuthorizationToken(authorizationToken)
                .setRequestMethod(HttpRequestMethod.GET)
                .build();

        return apiRequest.execute(SystemResource.class, false);
    }
}
