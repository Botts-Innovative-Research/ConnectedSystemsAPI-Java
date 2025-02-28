package org.connectedsystems.serialization;

import com.google.gson.*;
import org.vast.util.TimeExtent;

import java.lang.reflect.Type;
import java.util.List;

public class TimeExtentSerializer implements JsonSerializer<TimeExtent>, JsonDeserializer<TimeExtent> {
    @Override
    public JsonElement serialize(TimeExtent timeExtent, Type type, JsonSerializationContext context) {
        List<String> validTimeList = List.of(timeExtent.begin().toString(), timeExtent.end().toString());
        return context.serialize(validTimeList);
    }

    @Override
    public TimeExtent deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()) {
            // Time extent
            List<String> validTimeList = context.deserialize(json, List.class);
            if (validTimeList.size() != 2) {
                throw new JsonParseException("Invalid validTime array, expected exactly 2 elements.");
            }
            return TimeExtent.parse(validTimeList.get(0) + "/" + validTimeList.get(1));
        } else if (json.isJsonPrimitive()) {
            // Time instant
            String validTime = context.deserialize(json, String.class);
            return TimeExtent.parse(validTime);
        } else {
            throw new JsonParseException("Invalid validTime, expected an array or a string.");
        }
    }
}
