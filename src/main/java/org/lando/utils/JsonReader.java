package org.lando.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {
    public static JsonObject getJsonData(String filePath) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file in path: " + filePath, e);
        }
    }
}
