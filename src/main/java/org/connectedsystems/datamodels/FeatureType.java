package org.connectedsystems.datamodels;

public enum FeatureType {
    SENSOR("http://www.w3.org/ns/sosa/Sensor", "sosa:Sensor", "Sensor"),
    ACTUATOR("http://www.w3.org/ns/sosa/Actuator", "sosa:Actuator", "Actuator"),
    PLATFORM("http://www.w3.org/ns/sosa/Platform", "sosa:Platform", "Platform"),
    SAMPLER("http://www.w3.org/ns/sosa/Sampler", "sosa:Sampler", "Sampler"),
    SYSTEM("http://www.w3.org/ns/ssn/asdasdasdasd", "ssn:System", "System");

    private final String url;
    private final String curie;
    private final String token;

    FeatureType(String url, String curie, String token) {
        this.url = url;
        this.curie = curie;
        this.token = token;
    }

    /**
     * Converts a string to a FeatureType enum.
     *
     * @param value The string to convert.
     *              It can be a URL, CURIE, or a simple token.
     * @return The corresponding FeatureType enum.
     * @throws IllegalArgumentException if the string does not match any FeatureType.
     */
    public static FeatureType fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (FeatureType featureType : FeatureType.values()) {
            if (featureType.getUrl().equalsIgnoreCase(value)) {
                return featureType;
            }
        }

        for (FeatureType featureType : FeatureType.values()) {
            if (featureType.getCurie().equalsIgnoreCase(value)) {
                return featureType;
            }
        }

        for (FeatureType featureType : FeatureType.values()) {
            if (featureType.getToken().equalsIgnoreCase(value)) {
                return featureType;
            }
        }

        // Can these even happen?
        // The docs say it can, but these are not valid urls.
        // Best to translate them to the correct value.
        if (value.equalsIgnoreCase("http://www.w3.org/ns/sosa/System") || value.equalsIgnoreCase("sosa:System")) {
            return FeatureType.SYSTEM;
        }

        return null; // No match found
    }

    /**
     * Returns the URL of the feature type.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the CURIE of the feature type.
     */
    public String getCurie() {
        return curie;
    }

    /**
     * Returns the token of the feature type.
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns the URL of the feature type.
     */
    @Override
    public String toString() {
        return url;
    }
}
