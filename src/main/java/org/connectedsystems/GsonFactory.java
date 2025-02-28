package org.connectedsystems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.opengis.swe.v20.DataComponent;
import net.opengis.swe.v20.DataRecord;
import org.connectedsystems.datamodels.FeatureType;
import org.connectedsystems.serialization.DataComponentSerializer;
import org.connectedsystems.serialization.DataRecordSerializer;
import org.connectedsystems.serialization.FeatureTypeSerializer;
import org.connectedsystems.serialization.TimeExtentSerializer;
import org.vast.util.TimeExtent;

public class GsonFactory {
    public static final Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(FeatureType.class, new FeatureTypeSerializer())
                .registerTypeAdapter(TimeExtent.class, new TimeExtentSerializer())
                .registerTypeAdapter(DataComponent.class, new DataComponentSerializer())
                .registerTypeAdapter(DataRecord.class, new DataRecordSerializer())
                .create();
    }

    private GsonFactory() {
        // Private constructor to prevent instantiation
    }
}
