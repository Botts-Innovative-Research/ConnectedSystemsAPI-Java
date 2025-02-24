package org.connectedsystems.datamodels;

public class SystemResource {
    private final String type;
    private final String id;
    private final Properties properties;

    public SystemResource(String type, String id, Properties properties) {
        this.type = type;
        this.id = id;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }
}
