package com.pacific.domain.dto.jvm;

/**
 * Created by Fe on 16/7/11.
 */
public class JVMMemoryDto extends BaseDto {

    // Heap
    private long heapMemoryCommitted;
    private long heapMemoryInit;
    private long heapMemoryMax;
    private long heapMemoryUsed;

    // NonHeap
    private long nonHeapMemoryCommitted;
    private long nonHeapMemoryInit;
    private long nonHeapMemoryMax;
    private long nonHeapMemoryUsed;

    // PermGen
    private long permGenCommitted;
    private long permGenInit;
    private long permGenMax;
    private long permGenUsed;

    // OldGen
    private long oldGenCommitted;
    private long oldGenInit;
    private long oldGenMax;
    private long oldGenUsed;

    // EdenSpace
    private long edenSpaceCommitted;
    private long edenSpaceInit;
    private long edenSpaceMax;
    private long edenSpaceUsed;

    // Survivor
    private long survivorCommitted;
    private long survivorInit;
    private long survivorMax;
    private long survivorUsed;

    public long getHeapMemoryCommitted() {
        return heapMemoryCommitted;
    }

    public void setHeapMemoryCommitted(long heapMemoryCommitted) {
        this.heapMemoryCommitted = heapMemoryCommitted;
    }

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

    public long getNonHeapMemoryCommitted() {
        return nonHeapMemoryCommitted;
    }

    public void setNonHeapMemoryCommitted(long nonHeapMemoryCommitted) {
        this.nonHeapMemoryCommitted = nonHeapMemoryCommitted;
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

    public long getPermGenCommitted() {
        return permGenCommitted;
    }

    public void setPermGenCommitted(long permGenCommitted) {
        this.permGenCommitted = permGenCommitted;
    }

    public long getPermGenInit() {
        return permGenInit;
    }

    public void setPermGenInit(long permGenInit) {
        this.permGenInit = permGenInit;
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

    public long getOldGenCommitted() {
        return oldGenCommitted;
    }

    public void setOldGenCommitted(long oldGenCommitted) {
        this.oldGenCommitted = oldGenCommitted;
    }

    public long getOldGenInit() {
        return oldGenInit;
    }

    public void setOldGenInit(long oldGenInit) {
        this.oldGenInit = oldGenInit;
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

    public long getEdenSpaceCommitted() {
        return edenSpaceCommitted;
    }

    public void setEdenSpaceCommitted(long edenSpaceCommitted) {
        this.edenSpaceCommitted = edenSpaceCommitted;
    }

    public long getEdenSpaceInit() {
        return edenSpaceInit;
    }

    public void setEdenSpaceInit(long edenSpaceInit) {
        this.edenSpaceInit = edenSpaceInit;
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

    public long getSurvivorCommitted() {
        return survivorCommitted;
    }

    public void setSurvivorCommitted(long survivorCommitted) {
        this.survivorCommitted = survivorCommitted;
    }

    public long getSurvivorInit() {
        return survivorInit;
    }

    public void setSurvivorInit(long survivorInit) {
        this.survivorInit = survivorInit;
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
