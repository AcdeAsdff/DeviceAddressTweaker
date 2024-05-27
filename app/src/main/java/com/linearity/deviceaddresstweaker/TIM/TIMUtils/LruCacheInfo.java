package com.linearity.deviceaddresstweaker.TIM.TIMUtils;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class LruCacheInfo extends CacheInfo {
    public int evictionCount;

    public LruCacheInfo(int i, int i2) {
        super(i, i2);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LruCacheInfo{");
        sb.append("tagId=").append(this.tagId);
        sb.append(", capacity=").append(this.capacity);
        sb.append(", size=").append(this.size);
        sb.append(", getCount=").append(this.getCount);
        sb.append(", putCount=").append(this.putCount);
        sb.append(", removeCount=").append(this.removeCount);
        sb.append(", hitCount=").append(this.hitCount);
        sb.append(", missCount=").append(this.missCount);
        sb.append(", lifeTime=").append(this.lifeTime);
        sb.append(", gapTime=").append(this.gapTime);
        sb.append(", evictionCount=").append(this.evictionCount);
        sb.append('}');
        return sb.toString();
    }
}
