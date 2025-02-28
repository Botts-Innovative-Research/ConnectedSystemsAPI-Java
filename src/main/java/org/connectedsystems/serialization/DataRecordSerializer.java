package org.connectedsystems.serialization;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.opengis.swe.v20.DataRecord;
import org.vast.swe.SWEJsonBindings;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

public class DataRecordSerializer implements JsonSerializer<DataRecord>, JsonDeserializer<DataRecord> {
    @Override
    public JsonElement serialize(DataRecord src, Type typeOfSrc, JsonSerializationContext context) {
        SWEJsonBindings sweJsonBindings = new SWEJsonBindings();
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            sweJsonBindings.writeDataComponent(jsonWriter, src, false);
            jsonWriter.flush();
            jsonWriter.close();
            stringWriter.flush();
            stringWriter.close();
            return JsonParser.parseString(stringWriter.toString());
        } catch (IOException e) {
            throw new JsonParseException("Error serializing DataRecord", e);
        }
    }

    @Override
    public DataRecord deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SWEJsonBindings sweJsonBindings = new SWEJsonBindings();
        JsonReader jsonReader = new JsonReader(new StringReader(json.toString()));
        try {
            return sweJsonBindings.readDataRecord(jsonReader);
        } catch (IOException e) {
            throw new JsonParseException("Error deserializing DataRecord", e);
        }
    }
}
