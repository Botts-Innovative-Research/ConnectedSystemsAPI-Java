package org.connectedsystems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.opengis.swe.v20.DataComponent;
import net.opengis.swe.v20.DataRecord;
import org.connectedsystems.datamodels.FeatureType;
import org.connectedsystems.serialization.*;
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

    /**
     * Used for serialization of DataBlock objects, which require a schema to be deserialized.
     *
     * @param resultSchema The schema to use for deserialization of DataBlock objects.
     * @return A Gson instance with the necessary TypeAdapters registered.
     */
    public static Gson createGson(DataComponent resultSchema) {
        return new GsonBuilder()
                .registerTypeAdapter(FeatureType.class, new FeatureTypeSerializer())
                .registerTypeAdapter(TimeExtent.class, new TimeExtentSerializer())
                .registerTypeAdapter(DataComponent.class, new DataComponentSerializer())
                .registerTypeAdapter(DataRecord.class, new DataRecordSerializer())
                .registerTypeAdapterFactory(new DataBlockTypeAdapterFactory(resultSchema))
                .create();
    }
}
