package org.connectedsystems.datamodels;

import org.vast.util.TimeExtent;

public class Properties {
    protected FeatureType featureType;
    protected String uid;
    protected String name;
    protected String description;
    protected String assetType;
    protected TimeExtent validTime;

    /**
     * Identifier of the feature, either a URI, a CURIE, or a simple token
     */
    public FeatureType getFeatureType() {
        return featureType;
    }

    /**
     * Globally unique identifier of the feature.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Human-readable name of the feature.
     */
    public String getName() {
        return name;
    }

    /**
     * Human-readable description of the feature.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Type of asset represented by this system.
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * Time period during which the system description is valid.
     */
    public TimeExtent getValidTime() {
        return validTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("featureType: ").append(featureType).append(", ");
        sb.append("uid: ").append(uid).append(", ");
        sb.append("name: ").append(name).append(", ");
        if (description != null)
            sb.append("description: ").append(description).append(", ");
        if (assetType != null)
            sb.append("assetType: ").append(assetType).append(", ");
        if (validTime != null)
            sb.append("validTime: ").append(validTime).append(", ");
        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * Builder for the Properties class.
     */
    public static class Builder {
        private final Properties properties;

        public Builder() {
            properties = new Properties();
        }

        /**
         * Identifier of the feature, either a URI, a CURIE, or a simple token
         */
        public Builder setFeatureType(FeatureType featureType) {
            properties.featureType = featureType;
            return this;
        }

        /**
         * Globally unique identifier of the feature.
         */
        public Builder setUid(String uid) {
            properties.uid = uid;
            return this;
        }

        /**
         * Human-readable name of the feature.
         */
        public Builder setName(String name) {
            properties.name = name;
            return this;
        }

        /**
         * Human-readable description of the feature.
         */
        public Builder setDescription(String description) {
            properties.description = description;
            return this;
        }

        /**
         * Type of asset represented by this system.
         */
        public Builder setAssetType(String assetType) {
            properties.assetType = assetType;
            return this;
        }

        /**
         * Time period during which the system description is valid.
         */
        public Builder setValidTime(TimeExtent validTime) {
            properties.validTime = validTime;
            return this;
        }

        /**
         * Builds the Properties object.
         *
         * @return The built Properties object.
         * @throws IllegalStateException if any required fields are not set.
         */
        public Properties build() {
            if (properties.featureType == null)
                throw new IllegalStateException("FeatureType must be set");
            if (properties.uid == null)
                throw new IllegalStateException("UID must be set");
            if (properties.name == null)
                throw new IllegalStateException("Name must be set");
            if (properties.uid.length() < 12)
                throw new IllegalStateException("UID must be at least 12 characters long");

            return properties;
        }
    }
}
