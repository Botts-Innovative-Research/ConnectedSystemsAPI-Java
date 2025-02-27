package org.connectedsystems.datamodels;

import org.vast.util.TimeExtent;

public class Properties {
    protected String uid;
    protected FeatureType featureType;
    protected String name;
    protected String description;
    protected String assetType;
    protected TimeExtent validTime;

    /**
     * Globally unique identifier of the feature.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Identifier of the feature, either a URI, a CURIE, or a simple token
     */
    public FeatureType getFeatureType() {
        return featureType;
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

    public static class Builder {
        private final Properties properties;

        public Builder() {
            properties = new Properties();
        }

        public Builder setUid(String uid) {
            properties.uid = uid;
            return this;
        }

        public Builder setFeatureType(FeatureType featureType) {
            properties.featureType = featureType;
            return this;
        }

        public Builder setName(String name) {
            properties.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            properties.description = description;
            return this;
        }

        public Builder setAssetType(String assetType) {
            properties.assetType = assetType;
            return this;
        }

        public Builder setValidTime(TimeExtent validTime) {
            properties.validTime = validTime;
            return this;
        }

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
