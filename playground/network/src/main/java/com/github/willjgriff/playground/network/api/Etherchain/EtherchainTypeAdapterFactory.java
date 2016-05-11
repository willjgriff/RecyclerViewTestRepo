package com.github.willjgriff.playground.network.api.Etherchain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Will on 19/04/2016.
 */
public class EtherchainTypeAdapterFactory implements TypeAdapterFactory {

    Type mExpectedType;
    boolean mSingleItem;

    public EtherchainTypeAdapterFactory(boolean singleItem) {
        this(singleItem, null);
    }

    /**
     * Used to prevent slow processing of large JSON responses with multiple object types.
     * If we don't make this check every object in the JSON response will attempt to be read
     * using the long process below even if it isn't the {@link Type} we're trying to adapt.
     *
     * @param singleItem do we expect a list or a single item
     * @param expectedType response model type
     */
    public EtherchainTypeAdapterFactory(boolean singleItem, Type expectedType) {
        mSingleItem = singleItem;
        mExpectedType = expectedType;
    }

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        if (mExpectedType != null && !type.getType().equals(mExpectedType)) {
            return null;
        }

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has("data") && jsonObject.get("data").isJsonArray()) {
                        if (mSingleItem) {
                            jsonElement = jsonObject.get("data").getAsJsonArray().get(0);
                        } else {
                            jsonElement = jsonObject.get("data").getAsJsonArray();
                        }
                    }
                }

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
