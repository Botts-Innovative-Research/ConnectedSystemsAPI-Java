package org.connectedsystems;

import java.util.Base64;

/**
 * Class representing the Connected Systems API.
 * Using the various API classes, you can interact with a server that implements the Connected Systems API.
 */
public class ConnectedSystemsAPI {
    protected final String apiRoot;
    protected final String authorizationToken;
    protected final SystemsAPI systemsAPI;
    protected final DataStreamsAPI dataStreamsAPI;

    /**
     * Constructs a ConnectedSystemsAPI object with the given API root and authentication token.
     *
     * @param apiRoot             The root URL of the API, e.g., "localhost:8181/sensorhub/api".
     * @param authenticationToken The authentication token to use for API requests.
     */
    public ConnectedSystemsAPI(String apiRoot, String authenticationToken) {
        this.apiRoot = apiRoot;
        this.authorizationToken = authenticationToken;
        this.systemsAPI = new SystemsAPI(this);
        this.dataStreamsAPI = new DataStreamsAPI(this);
    }

    /**
     * Constructs a ConnectedSystemsAPI object with the given API root, username, and password.
     * The username and password are Base64-encoded to create the authentication token.
     *
     * @param apiRoot  The root URL of the API, e.g., "localhost:8181/sensorhub/api".
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public ConnectedSystemsAPI(String apiRoot, String username, String password) {
        this(apiRoot, Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
    }

    public String getApiRoot() {
        return apiRoot;
    }

    /**
     * Get the {@link SystemsAPI} object for this Connected Systems API.
     * The SystemsAPI object provides methods for interacting with the systems endpoint of the API,
     * such as listing all systems, getting a specific system, and getting subsystems.
     */
    public SystemsAPI getSystemsAPI() {
        return systemsAPI;
    }

    /**
     * Get the {@link DataStreamsAPI} object for this Connected Systems API.
     * The DataStreamsAPI object provides methods for interacting with the data streams endpoint of the API,
     * such as listing all data streams, getting a specific data stream, and creating a new data stream.
     */
    public DataStreamsAPI getDataStreamsAPI() {
        return dataStreamsAPI;
    }
}