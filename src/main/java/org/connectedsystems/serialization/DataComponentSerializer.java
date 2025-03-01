package org.connectedsystems.serialization;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.opengis.swe.v20.DataComponent;
import net.opengis.swe.v20.DataRecord;
import net.opengis.swe.v20.Time;
import org.vast.data.ScalarIterator;
import org.vast.swe.SWEHelper;
import org.vast.swe.SWEJsonBindings;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

import static org.connectedsystems.util.SWECommonUtils.OM_COMPONENTS_DEF;
import static org.connectedsystems.util.SWECommonUtils.OM_COMPONENTS_FILTER;

public class DataComponentSerializer implements JsonSerializer<DataComponent>, JsonDeserializer<DataComponent> {
    @Override
    public JsonElement serialize(DataComponent src, Type typeOfSrc, JsonSerializationContext context) {
        // Remove time and FOI components if any
        var dataStruct = src.copy();
        if (dataStruct instanceof DataRecord dataRecord) {
            dataRecord.getFieldList().removeIf(dataComponent -> !OM_COMPONENTS_FILTER.accept(dataComponent));
        }

        SWEJsonBindings sweJsonBindings = new SWEJsonBindings();
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            sweJsonBindings.writeDataComponent(jsonWriter, dataStruct, false);
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
            var swe = new SWEHelper();
            DataComponent resultStruct = sweJsonBindings.readDataComponent(jsonReader);

            // Add a time component if not present
            if (!hasTimeStamp(resultStruct)) {
                if (resultStruct instanceof DataRecord dataRecord) {
                    var ts = swe.createTime()
                            .name("time")
                            .asPhenomenonTimeIsoUTC()
                            .build();
                    dataRecord.getFieldList().add(0, ts);
                } else {
                    resultStruct = swe.createRecord()
                            .name(resultStruct.getName() + "_rec")
                            .addField("time", swe.createTime().asPhenomenonTimeIsoUTC())
                            .addField(resultStruct.getName(), resultStruct)
                            .build();
                }
            }
            return resultStruct;
        } catch (IOException e) {
            throw new JsonParseException("Error deserializing DataComponent", e);
        }
    }

    protected boolean hasTimeStamp(DataComponent resultStruct) {
        var it = new ScalarIterator(resultStruct);
        while (it.hasNext()) {
            var c = it.next();
            if (c instanceof Time && OM_COMPONENTS_DEF.contains(c.getDefinition()))
                return true;
        }

        return false;
    }
}
