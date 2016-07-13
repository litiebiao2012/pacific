package com.pacific.domain.dto.jvm;

/**
 * Created by Fe on 16/7/11.
 */
public class JVMMemoryDto extends BaseDto {

    // Heap
    private long heapMemoryInit;
    private long heapMemoryMax;
    private long heapMemoryUsed;

    // NonHeap
    private long nonHeapMemoryInit;
    private long nonHeapMemoryMax;
    private long nonHeapMemoryUsed;

    // PermGen
    private long permGenMax;
    private long permGenUsed;

    // OldGen
    private long oldGenMax;
    private long oldGenUsed;

    // EdenSpace
    private long edenSpaceMax;
    private long edenSpaceUsed;

    // Survivor
    private long survivorMax;
    private long survivorUsed;

    public long getHeapMemoryInit() {
        return heapMemoryInit;
    }

    public void setHeapMemoryInit(long heapMemoryInit) {
        this.heapMemoryInit = heapMemoryInit;
    }

    public long getHeapMemoryMax() {
        return heapMemoryMax;
    }

    public void setHeapMemoryMax(long heapMemoryMax) {
        this.heapMemoryMax = heapMemoryMax;
    }

    public long getHeapMemoryUsed() {
        return heapMemoryUsed;
    }

    public void setHeapMemoryUsed(long heapMemoryUsed) {
        this.heapMemoryUsed = heapMemoryUsed;
    }



    public long getNonHeapMemoryInit() {
        return nonHeapMemoryInit;
    }

    public void setNonHeapMemoryInit(long nonHeapMemoryInit) {
        this.nonHeapMemoryInit = nonHeapMemoryInit;
    }

    public long getNonHeapMemoryMax() {
        return nonHeapMemoryMax;
    }

    public void setNonHeapMemoryMax(long nonHeapMemoryMax) {
        this.nonHeapMemoryMax = nonHeapMemoryMax;
    }

    public long getNonHeapMemoryUsed() {
        return nonHeapMemoryUsed;
    }

    public void setNonHeapMemoryUsed(long nonHeapMemoryUsed) {
        this.nonHeapMemoryUsed = nonHeapMemoryUsed;
    }

    public long getPermGenMax() {
        return permGenMax;
    }

    public void setPermGenMax(long permGenMax) {
        this.permGenMax = permGenMax;
    }

    public long getPermGenUsed() {
        return permGenUsed;
    }

    public void setPermGenUsed(long permGenUsed) {
        this.permGenUsed = permGenUsed;
    }

    public long getOldGenMax() {
        return oldGenMax;
    }

    public void setOldGenMax(long oldGenMax) {
        this.oldGenMax = oldGenMax;
    }

    public long getOldGenUsed() {
        return oldGenUsed;
    }

    public void setOldGenUsed(long oldGenUsed) {
        this.oldGenUsed = oldGenUsed;
    }

    public long getEdenSpaceMax() {
        return edenSpaceMax;
    }

    public void setEdenSpaceMax(long edenSpaceMax) {
        this.edenSpaceMax = edenSpaceMax;
    }

    public long getEdenSpaceUsed() {
        return edenSpaceUsed;
    }

    public void setEdenSpaceUsed(long edenSpaceUsed) {
        this.edenSpaceUsed = edenSpaceUsed;
    }

    public long getSurvivorMax() {
        return survivorMax;
    }

    public void setSurvivorMax(long survivorMax) {
        this.survivorMax = survivorMax;
    }

    public long getSurvivorUsed() {
        return survivorUsed;
    }

    public void setSurvivorUsed(long survivorUsed) {
        this.survivorUsed = survivorUsed;
    }
}
