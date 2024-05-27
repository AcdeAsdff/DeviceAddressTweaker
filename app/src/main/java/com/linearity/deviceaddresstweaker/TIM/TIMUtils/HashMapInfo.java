package com.linearity.deviceaddresstweaker.TIM.TIMUtils;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class HashMapInfo extends CacheInfo {
    public int traversalCount;

    public HashMapInfo(int i, int i2, int i3) {
        super(i, i2);
        this.mItemSize = i3;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HashMapInfo{");
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
        sb.append(", mMemorySize=").append(this.mMemorySize);
        sb.append(", traversalCount=").append(this.traversalCount);
        sb.append('}');
        return sb.toString();
    }
}
