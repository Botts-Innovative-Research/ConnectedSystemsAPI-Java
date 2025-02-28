package org.connectedsystems.datamodels;

import java.util.List;

public class SystemResource {
    protected String type;
    protected String id;
    protected Properties properties;
    protected List<Link> links;

    public String getType() {
        return type;
    }

    /**
     * Local ID of the feature (ignored on create or update).
     */
    public String getId() {
        return id;
    }

    /**
     * Feature properties.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Links to related resources.
     */
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("type: ").append(type).append(", ");
        if (id != null)
            sb.append("id: ").append(id).append(", ");
        sb.append("properties: ").append(properties).append(", ");
        if (links != null)
            sb.append("links: ").append(links).append(", ");
        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }

    public static class Builder {
        private final SystemResource systemResource;

        public Builder() {
            systemResource = new SystemResource();
        }

        /**
         * Local ID of the feature (ignored on create or update).
         */
        public Builder setId(String id) {
            systemResource.id = id;
            return this;
        }

        /**
         * Feature properties.
         */
        public Builder setProperties(Properties properties) {
            systemResource.properties = properties;
            return this;
        }

        /**
         * Links to related resources.
         */
        public Builder setLinks(List<Link> links) {
            systemResource.links = links;
            return this;
        }

        /**
         * Build the SystemResource object.
         *
         * @return The built SystemResource object.
         * @throws IllegalStateException if properties are not set.
         */
        public SystemResource build() {
            if (systemResource.properties == null)
                throw new IllegalStateException("Properties must be set.");

            // Set the type to "Feature" as per the specification
            systemResource.type = "Feature";
            return systemResource;
        }
    }
}
