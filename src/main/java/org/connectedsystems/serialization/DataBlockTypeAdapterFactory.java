package org.connectedsystems.serialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import net.opengis.swe.v20.DataBlock;
import net.opengis.swe.v20.DataComponent;

/**
 * Custom TypeAdapterFactory for DataBlock objects, since they require a schema to be deserialized.
 */
public class DataBlockTypeAdapterFactory implements TypeAdapterFactory {
    private final DataComponent resultSchema;

    public DataBlockTypeAdapterFactory(DataComponent resultSchema) {
        this.resultSchema = resultSchema;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!DataBlock.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        TypeAdapter<DataBlock> defaultAdapter = gson.getDelegateAdapter(this, TypeToken.get(DataBlock.class));
        return (TypeAdapter<T>) new DataBlockSerializer(defaultAdapter, resultSchema);
    }
}