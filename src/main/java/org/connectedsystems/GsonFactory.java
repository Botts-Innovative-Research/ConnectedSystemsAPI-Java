package org.connectedsystems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.connectedsystems.datamodels.FeatureType;
import org.connectedsystems.serialization.FeatureTypeSerializer;
import org.connectedsystems.serialization.TimeExtentSerializer;
import org.vast.util.TimeExtent;

public class GsonFactory {
    public static final Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(FeatureType.class, new FeatureTypeSerializer())
                .registerTypeAdapter(TimeExtent.class, new TimeExtentSerializer())
                .create();
    }

    private GsonFactory() {
        // Private constructor to prevent instantiation
    }
}
