package org.connectedsystems.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.opengis.swe.v20.DataBlock;
import net.opengis.swe.v20.DataComponent;
import org.vast.swe.fast.JsonDataParserGson;
import org.vast.swe.fast.JsonDataWriterGson;

import java.io.IOException;
import java.io.StringReader;

import static org.connectedsystems.util.SWECommonUtils.OM_COMPONENTS_FILTER;

public class DataBlockSerializer extends TypeAdapter<DataBlock> {
    private final DataComponent resultSchema;

    public DataBlockSerializer(DataComponent resultSchema) {
        this.resultSchema = resultSchema;
    }

    @Override
    public void write(JsonWriter out, DataBlock value) throws IOException {
        var sweWriter = new JsonDataWriterGson(out);
        sweWriter.setDataComponents(resultSchema);
        sweWriter.setDataComponentFilter(OM_COMPONENTS_FILTER);
        sweWriter.write(value);
        sweWriter.flush();
    }

    @Override
    public DataBlock read(JsonReader in) throws IOException {
        JsonElement jsonElement = JsonParser.parseReader(in);
        JsonReader jsonReader = new JsonReader(new StringReader(jsonElement.toString()));
        var sweParser = new JsonDataParserGson(jsonReader);
        sweParser.setDataComponents(resultSchema);
        sweParser.setDataComponentFilter(OM_COMPONENTS_FILTER);
        return sweParser.parseNextBlock();
    }
}
