package org.connectedsystems.serialization;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.opengis.swe.v20.DataComponent;
import org.vast.swe.SWEJsonBindings;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

public class DataComponentSerializer implements JsonSerializer<DataComponent>, JsonDeserializer<DataComponent> {
    @Override
    public JsonElement serialize(DataComponent src, Type typeOfSrc, JsonSerializationContext context) {
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
            throw new JsonParseException("Error serializing DataComponent", e);
        }
    }

    @Override
    public DataComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SWEJsonBindings sweJsonBindings = new SWEJsonBindings();
        JsonReader jsonReader = new JsonReader(new StringReader(json.toString()));
        try {
            return sweJsonBindings.readDataComponent(jsonReader);
        } catch (IOException e) {
            throw new JsonParseException("Error deserializing DataComponent", e);
        }
    }
}
