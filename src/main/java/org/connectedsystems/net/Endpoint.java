package org.connectedsystems.net;

/**
 * Enum representing the endpoints of the Connected Systems API.
 */
public enum Endpoint {
    SYSTEMS_COLLECTION("systems"),
    SUBSYSTEMS_COLLECTION("subsystems"),
    ;

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    /**
     * Get the path of this endpoint.
     *
     * @return The path of this endpoint.
     */
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
