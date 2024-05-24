package com.linearity.utils;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class ListenerHashMap<K, V> extends HashMap<K, V> {

    @Nullable
    @Override
    public V get(@Nullable Object key) {
        LoggerLog("[linearity-ListenerHashMap]","getting " + key);
        return super.get(key);
    }
}
