package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class JVMMemory implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private Long heapMemoryInit;

    private Long heapMemoryMax;

    private Long heapMemoryUsed;

    private Long nonHeapMemoryInit;

    private Long nonHeapMemoryMax;

    private Long nonHeapMemoryUsed;

    private Long permGenMax;

    private Long permGenUsed;

    private Long oldGenMax;

    private Long oldGenUsed;

    private Long edenSpaceMax;

    private Long edenSpaceUsed;

    private Long survivorMax;

    private Long survivorUsed;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode == null ? null : applicationCode.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getHeapMemoryInit() {
        return heapMemoryInit;
    }

    public void setHeapMemoryInit(Long heapMemoryInit) {
        this.heapMemoryInit = heapMemoryInit;
    }

    public Long getHeapMemoryMax() {
        return heapMemoryMax;
    }

    public void setHeapMemoryMax(Long heapMemoryMax) {
        this.heapMemoryMax = heapMemoryMax;
    }

    public Long getHeapMemoryUsed() {
        return heapMemoryUsed;
    }

    public void setHeapMemoryUsed(Long heapMemoryUsed) {
        this.heapMemoryUsed = heapMemoryUsed;
    }

    public Long getNonHeapMemoryInit() {
        return nonHeapMemoryInit;
    }

    public void setNonHeapMemoryInit(Long nonHeapMemoryInit) {
        this.nonHeapMemoryInit = nonHeapMemoryInit;
    }

    public Long getNonHeapMemoryMax() {
        return nonHeapMemoryMax;
    }

    public void setNonHeapMemoryMax(Long nonHeapMemoryMax) {
        this.nonHeapMemoryMax = nonHeapMemoryMax;
    }

    public Long getNonHeapMemoryUsed() {
        return nonHeapMemoryUsed;
    }

    public void setNonHeapMemoryUsed(Long nonHeapMemoryUsed) {
        this.nonHeapMemoryUsed = nonHeapMemoryUsed;
    }

    public Long getPermGenMax() {
        return permGenMax;
    }

    public void setPermGenMax(Long permGenMax) {
        this.permGenMax = permGenMax;
    }

    public Long getPermGenUsed() {
        return permGenUsed;
    }

    public void setPermGenUsed(Long permGenUsed) {
        this.permGenUsed = permGenUsed;
    }

    public Long getOldGenMax() {
        return oldGenMax;
    }

    public void setOldGenMax(Long oldGenMax) {
        this.oldGenMax = oldGenMax;
    }

    public Long getOldGenUsed() {
        return oldGenUsed;
    }

    public void setOldGenUsed(Long oldGenUsed) {
        this.oldGenUsed = oldGenUsed;
    }

    public Long getEdenSpaceMax() {
        return edenSpaceMax;
    }

    public void setEdenSpaceMax(Long edenSpaceMax) {
        this.edenSpaceMax = edenSpaceMax;
    }

    public Long getEdenSpaceUsed() {
        return edenSpaceUsed;
    }

    public void setEdenSpaceUsed(Long edenSpaceUsed) {
        this.edenSpaceUsed = edenSpaceUsed;
    }

    public Long getSurvivorMax() {
        return survivorMax;
    }

    public void setSurvivorMax(Long survivorMax) {
        this.survivorMax = survivorMax;
    }

    public Long getSurvivorUsed() {
        return survivorUsed;
    }

    public void setSurvivorUsed(Long survivorUsed) {
        this.survivorUsed = survivorUsed;
    }
}