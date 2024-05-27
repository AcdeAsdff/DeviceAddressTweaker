package com.linearity.deviceaddresstweaker.TIM.TIMUtils;

import static com.linearity.deviceaddresstweaker.TIM.HookTIMClass.modifyMessage;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class QQConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {
    private static IMemoryManager sManager;
    private long creatTime;
    private long firstUseTime;
    private Set<K> getInfos;
    private final Object lock;
    private HashMapInfo mHashMapInfo;

    @Deprecated
    public QQConcurrentHashMap(int i, int i2) {
        super(i2);
        this.lock = new Object();
        init(i, i2, 1);
    }

    public QQConcurrentHashMap(int i, int i2, int i3) {
        super(i2);
        this.lock = new Object();
        init(i, i2, i3);
    }

    private void init(int i, int i2, int i3) {
        this.mHashMapInfo = new HashMapInfo(i, i2, i3);
        this.getInfos = new HashSet<>();
        this.creatTime = System.currentTimeMillis();
        if (sManager != null) {
            sManager.addConCurrentHashMap(this);
        }
    }

    public static void setManager(IMemoryManager iMemoryManager) {
        if (iMemoryManager != null) {
            sManager = iMemoryManager;
        }
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V get(@NonNull Object obj) {
        V v = (V) super.get(obj);
        if (v != null) {
            synchronized (this.lock) {
                this.getInfos.add((K) obj);
            }
            this.mHashMapInfo.hitCount++;
        } else {
            this.mHashMapInfo.missCount++;
        }
        if (this.firstUseTime == 0) {
            this.firstUseTime = System.currentTimeMillis();
        }
        return v;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NonNull Object obj) {
        boolean containsKey = super.containsKey(obj);
        if (!containsKey) {
            this.mHashMapInfo.missCount++;
        } else {
            this.mHashMapInfo.hitCount++;
        }
        if (this.firstUseTime == 0) {
            this.firstUseTime = System.currentTimeMillis();
        }
        return containsKey;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V put(@NonNull K k, @NonNull V v) {
        if (v.getClass().getName().equals("com.tencent.imcore.message.QQMessageFacade$Message")){
            modifyMessage(v);
        }
        V v2 = (V) super.put(k, v);
        this.mHashMapInfo.mMemorySize += sizeOf(k, v);
        if (v2 != null) {
            this.mHashMapInfo.mMemorySize -= sizeOf(k, v2);
        } else {
            this.mHashMapInfo.putCount++;
        }
        if (this.firstUseTime == 0) {
            this.firstUseTime = System.currentTimeMillis();
        }
        return v2;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public void putAll(@NonNull Map<? extends K, ? extends V> map) {
        super.putAll(map);
        this.mHashMapInfo.putCount += map.size();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V remove(@NonNull Object obj) {
        V v = (V) super.remove(obj);
        if (v != null) {
            this.mHashMapInfo.mMemorySize -= sizeOf((K) obj, v);
            this.mHashMapInfo.removeCount++;
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int sizeOf(K k, V v) {
        return this.mHashMapInfo.mItemSize;
    }

    @NonNull
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        this.mHashMapInfo.traversalCount++;
        return super.keySet();
    }

    @NonNull
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        this.mHashMapInfo.traversalCount++;
        return super.values();
    }

    @NonNull
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public Set<Entry<K, V>> entrySet() {
        this.mHashMapInfo.traversalCount++;
        return super.entrySet();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
        this.mHashMapInfo.mMemorySize = 0;
    }

    protected void clearMemory() {
    }

    public final void onClearOnLowMemory() {
        int i = this.mHashMapInfo.mMemorySize;
        clearMemory();
        int i2 = this.mHashMapInfo.mMemorySize;
        if (i > i2) {
            this.mHashMapInfo.mClearSize = i - i2;
            return;
        }
        this.mHashMapInfo.mClearSize = 0;
    }

    public CacheInfo getReportCacheInfo() {
        this.mHashMapInfo.size = super.size();
        synchronized (this.lock) {
            this.mHashMapInfo.getCount = this.getInfos.size();
        }
        this.mHashMapInfo.lifeTime = System.currentTimeMillis() - this.creatTime;
        if (this.firstUseTime != 0) {
            this.mHashMapInfo.gapTime = this.firstUseTime - this.creatTime;
        }
        return this.mHashMapInfo;
    }
}
