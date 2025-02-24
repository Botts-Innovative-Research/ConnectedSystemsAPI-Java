package org.connectedsystems.datamodels;

import java.util.List;

public class Properties {
    private final String uid;
    private final String featureType;
    private final String name;
    private final String description;
    private final List<String> validTime;

    public Properties(String uid, String featureType, String name, String description, List<String> validTime) {
        this.uid = uid;
        this.featureType = featureType;
        this.name = name;
        this.description = description;
        this.validTime = validTime;
    }

    public String getUid() {
        return uid;
    }

    public String getFeatureType() {
        return featureType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getValidTime() {
        return validTime;
    }
}
