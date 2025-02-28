package org.connectedsystems.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.opengis.swe.v20.DataBlock;
import net.opengis.swe.v20.DataComponent;
import org.vast.swe.fast.JsonDataParserGson;

import java.io.IOException;
import java.io.StringReader;

public class DataBlockSerializer extends TypeAdapter<DataBlock> {
    private final TypeAdapter<DataBlock> defaultAdapter;
    private final DataComponent resultSchema;

    public DataBlockSerializer(TypeAdapter<DataBlock> defaultAdapter, DataComponent resultSchema) {
        this.defaultAdapter = defaultAdapter;
        this.resultSchema = resultSchema;
    }

    @Override
    public void write(JsonWriter out, DataBlock value) throws IOException {
        defaultAdapter.write(out, value);
    }

    @Override
    public DataBlock read(JsonReader in) throws IOException {
        JsonElement jsonElement = JsonParser.parseReader(in);
        JsonReader jsonReader = new JsonReader(new StringReader(jsonElement.toString()));
        var sweParser = new JsonDataParserGson(jsonReader);
        sweParser.setDataComponents(resultSchema);
        return sweParser.parseNextBlock();
    }
}
