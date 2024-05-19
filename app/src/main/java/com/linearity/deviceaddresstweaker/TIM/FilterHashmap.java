package com.linearity.deviceaddresstweaker.TIM;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FilterHashmap extends HashMap<String,Object> {
    HashSet<String> filter = new HashSet<>();
    public FilterHashmap(String... filterStr){
        super();
        filter.addAll(Arrays.asList(filterStr));
    }
    @Nullable
    @Override
    public Object put(String key, Object value) {
        if (filter.contains(key)){
            return null;
        }
        return super.put(key, value);
    }
}
