package com.linearity.deviceaddresstweaker.TIM.TIMUtils;
import android.util.LruCache;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class QQLruCache<K, V> {
    public static IMemoryManager sManager;
    private long createTime;
    private long firstUseTime;
    private int getCount;
    private LruCache<K, V> mCache;
    private int mClearSize;
    private int mItemSize;
    private int removeCount;
    private int subNum;
    public int tagId;

    @Deprecated
    public QQLruCache(int i, int i2) {
        init(i, i2, 1);
    }

    public QQLruCache(int i, int i2, int i3) {
        init(i, i2, i3);
    }

    private void init(int i, int i2, int i3) {
        this.mCache = new LruCache<>(i2) { // from class: com.tencent.commonsdk.cache.QQLruCache.1
            @Override // android.support.v4.util.LruCache
            public void entryRemoved(boolean z, K k, V v, V v2) {
                QQLruCache.this.entryRemoved(z, k, v, v2);
                if (z) {
                    synchronized (this) {
                        if (v != null) {
                            if (v instanceof List) {
                                QQLruCache.this.subNum -= ((List) v).size();
                            }
                        }
                        if (v2 != null && (v2 instanceof List)) {
                            QQLruCache.this.subNum += ((List) v2).size();
                        }
                    }
                }
            }

            @Override // android.support.v4.util.LruCache
            public int sizeOf(K k, V v) {
                return QQLruCache.this.sizeOf(k, v);
            }
        };
        init(i, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void entryRemoved(boolean z, K k, V v, V v2) {
    }

    protected int sizeOf(K k, V v) {
        return 1;
    }

    private void init(int i, int i2) {
        this.tagId = i;
        this.mItemSize = i2;
        this.createTime = System.currentTimeMillis();
        if (sManager != null) {
            sManager.addQQLruCache(this);
        }
    }

    public static void setManager(IMemoryManager iMemoryManager) {
        if (iMemoryManager != null) {
            sManager = iMemoryManager;
        }
    }

    public V get(K k) {
        if (this.firstUseTime == 0) {
            this.firstUseTime = System.currentTimeMillis();
        }
        this.getCount++;
        return this.mCache.get(k);
    }

    public V put(K k, V v) {
        if (this.firstUseTime == 0) {
            this.firstUseTime = System.currentTimeMillis();
        }
        V put = this.mCache.put(k, v);
        synchronized (this) {
            if (v instanceof List) {
                if (put != null) {
                    this.subNum -= ((List) put).size();
                }
                this.subNum += ((List) v).size();
            }
        }
        return put;
    }

    public V remove(K k) {
        V remove = this.mCache.remove(k);
        synchronized (this) {
            this.removeCount++;
            if (remove != null && (remove instanceof List)) {
                this.subNum -= ((List) remove).size();
            }
        }
        return remove;
    }

    public boolean containsKey(K k) {
        return this.mCache.get(k) != null;
    }

    public int size() {
        return this.mCache.size();
    }

    public synchronized Map<K, V> snapshot() {
        return this.mCache.snapshot();
    }

    public void evictAll() {
        this.mCache.evictAll();
        this.subNum = 0;
    }

    protected void onClearMemory() {
    }

    public final void clearOnLowMemory(int i) {
        synchronized (this) {
            if (this.subNum > 0) {
                int i2 = this.subNum;
                this.mCache.trimToSize(i);
                this.mClearSize = (i2 - this.subNum) * this.mItemSize;
            } else {
                int size = this.mCache.size();
                this.mCache.trimToSize(i);
                this.mClearSize = (size - this.mCache.size()) * this.mItemSize;
            }
        }
        onClearMemory();
    }

    public synchronized CacheInfo getCacheInfos() {
        LruCacheInfo lruCacheInfo;
        lruCacheInfo = new LruCacheInfo(this.tagId, this.mCache.maxSize());
        lruCacheInfo.size = this.mCache.size();
        lruCacheInfo.mItemSize = this.mItemSize;
        if (this.subNum > 0) {
            lruCacheInfo.mMemorySize = this.subNum * this.mItemSize;
        } else {
            lruCacheInfo.mMemorySize = lruCacheInfo.size * this.mItemSize;
        }
        lruCacheInfo.mClearSize = this.mClearSize;
        lruCacheInfo.putCount = this.mCache.putCount();
        lruCacheInfo.getCount = this.getCount;
        lruCacheInfo.removeCount = this.removeCount;
        lruCacheInfo.evictionCount = this.mCache.evictionCount();
        lruCacheInfo.hitCount = this.mCache.hitCount();
        lruCacheInfo.missCount = this.mCache.missCount();
        lruCacheInfo.lifeTime = System.currentTimeMillis() - this.createTime;
        if (this.firstUseTime != 0) {
            lruCacheInfo.gapTime = System.currentTimeMillis() - this.createTime;
        }
        return lruCacheInfo;
    }
}
