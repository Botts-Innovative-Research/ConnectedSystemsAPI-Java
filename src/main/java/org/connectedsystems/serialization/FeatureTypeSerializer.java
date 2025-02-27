package org.connectedsystems.serialization;

import com.google.gson.*;
import org.connectedsystems.datamodels.FeatureType;

import java.lang.reflect.Type;

public class FeatureTypeSerializer implements JsonSerializer<FeatureType>, JsonDeserializer<FeatureType> {
    @Override
    public JsonElement serialize(FeatureType featureType, Type type, JsonSerializationContext context) {
        return context.serialize(featureType.toString());
    }

    @Override
    public FeatureType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return FeatureType.fromString(json.getAsString());
    }
}
