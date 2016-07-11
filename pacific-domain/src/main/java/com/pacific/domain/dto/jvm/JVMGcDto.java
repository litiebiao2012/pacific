package com.pacific.domain.dto.jvm;

/**
 * Created by Fe on 16/7/11.
 */
public class JVMGcDto extends BaseDto {
    private long youngGCCollectionCount;

    private long youngGCCollectionTime;

    private long fullGCCollectionCount;

    private long fullGCCollectionTime;

    public long getYoungGCCollectionCount() {
        return youngGCCollectionCount;
    }

    public void setYoungGCCollectionCount(long youngGCCollectionCount) {
        this.youngGCCollectionCount = youngGCCollectionCount;
    }

    public long getYoungGCCollectionTime() {
        return youngGCCollectionTime;
    }

    public void setYoungGCCollectionTime(long youngGCCollectionTime) {
        this.youngGCCollectionTime = youngGCCollectionTime;
    }

    public long getFullGCCollectionCount() {
        return fullGCCollectionCount;
    }

    public void setFullGCCollectionCount(long fullGCCollectionCount) {
        this.fullGCCollectionCount = fullGCCollectionCount;
    }

    public long getFullGCCollectionTime() {
        return fullGCCollectionTime;
    }

    public void setFullGCCollectionTime(long fullGCCollectionTime) {
        this.fullGCCollectionTime = fullGCCollectionTime;
    }
}
