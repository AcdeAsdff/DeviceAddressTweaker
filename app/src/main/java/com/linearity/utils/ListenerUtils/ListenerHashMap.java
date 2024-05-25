package com.linearity.utils.ListenerUtils;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class ListenerHashMap<K, V> extends HashMap<K, V> {
    public static final boolean useLogger = false;

    @Nullable
    @Override
    public V get(@Nullable Object key) {
        if (useLogger){
            LoggerLog("[linearity-ListenerHashMap]", "getting " + key);
        }
        return super.get(key);
    }
}
