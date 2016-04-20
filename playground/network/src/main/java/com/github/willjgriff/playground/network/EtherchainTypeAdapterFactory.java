//package com.github.willjgriff.playground.network;
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//import com.google.gson.TypeAdapterFactory;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonWriter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * Created by Will on 19/04/2016.
// */
//public class EtherchainTypeAdapterFactory<MODEL> implements TypeAdapterFactory {
//
//    @Override
//    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
//
//        return new TypeAdapter<T>() {
//            @Override
//            public void write(JsonWriter out, T value) throws IOException {
//            }
//
//            @Override
//            public T read(JsonReader in) throws IOException {
//                ArrayList<MODEL> featureList = new ArrayList<>();
//                String currName;
//                in.beginObject();
//                while (in.hasNext()) {
//                    currName = in.nextName();
//                    if (currName.equals("data")) {
//                        Gson gson  = new Gson();
//                        in.beginArray();
//                        while (in.hasNext()) {
//                            featureList.add(gson.fromJson(in.nextString(), ));
//                        }
//                    }
//                }
//                return (T) featureList;
//            }
//        };
//    }
//}
