package com.linearity.deviceaddresstweaker.TIM.TIMUtils;


import androidx.collection.LruCache;

/* loaded from: classes.dex */
public interface IMemoryManager {
    void addConCurrentHashMap(QQConcurrentHashMap qQConcurrentHashMap);

    void addHashMap(QQHashMap qQHashMap);

    void addLruCache(LruCache lruCache);

    void addQQLruCache(QQLruCache qQLruCache);
}
