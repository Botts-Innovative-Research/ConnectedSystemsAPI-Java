package org.connectedsystems.datamodels;

public enum FeatureType {
    SENSOR("http://www.w3.org/ns/sosa/Sensor"),
    ACTUATOR("http://www.w3.org/ns/sosa/Actuator"),
    PLATFORM("http://www.w3.org/ns/sosa/Platform"),
    SAMPLER("http://www.w3.org/ns/sosa/Sampler"),
    SYSTEM("http://www.w3.org/ns/sosa/System");

    private final String url;

    FeatureType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
