package org.connectedsystems.datamodels;

public class SystemResource {
    protected String type;
    protected String id;
    protected Properties properties;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

    public static class Builder {
        private final SystemResource systemResource;

        public Builder() {
            systemResource = new SystemResource();
        }

        public Builder setId(String id) {
            systemResource.id = id;
            return this;
        }

        public Builder setProperties(Properties properties) {
            systemResource.properties = properties;
            return this;
        }

        public SystemResource build() {
            if (systemResource.properties == null)
                throw new IllegalStateException("Properties must be set.");

            // Set the type to "Feature" as per the specification
            systemResource.type = "Feature";
            return systemResource;
        }
    }
}
