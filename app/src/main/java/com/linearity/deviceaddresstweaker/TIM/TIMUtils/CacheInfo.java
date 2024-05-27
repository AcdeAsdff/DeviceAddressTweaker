package com.linearity.deviceaddresstweaker.TIM.TIMUtils;

public class CacheInfo {
    public int capacity;
    public long gapTime;
    public int getCount;
    public int hitCount;
    public long lifeTime;
    public int mClearSize;
    public int mItemSize;
    public int mMemorySize;
    public int missCount;
    public int putCount;
    public int removeCount;
    public int size;
    public int tagId;

    public CacheInfo(int i, int i2) {
        this.tagId = i;
        this.capacity = i2;
    }
}
