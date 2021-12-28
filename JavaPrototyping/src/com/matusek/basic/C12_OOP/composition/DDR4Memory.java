package com.matusek.basic.C12_OOP.composition;

import java.util.HashMap;
import java.util.Map;

public class DDR4Memory implements Memory {

    private Map<String, String> data = new HashMap<>();

    @Override
    public void store(String key, String value) {
        data.put(key, value);
    }

    @Override
    public String get(String key) {
        return data.getOrDefault(key, "");
    }
}
