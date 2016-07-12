package com.pacific.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class JVMInfo implements Serializable {
    private Long id;

    private String applicationCode;

    private String clientIp;

    private Date createTime;

    private Date updateTime;

    private String hostName;

    private Date jvmStartTime;

    private String pid;

    private String inputArguments;

    private String arch;

    private Integer availableProcessors;

    private String osName;

    private String fileEncode;

    private String jvm;

    private String javaVersion;

    private String javaSpecificationVersion;

    private String javaHome;

    private String javaLibraryPath;

    private Integer loadedClassCount;

    private Long totalLoadedClassCount;

    private Long unloadedClassCount;

    private Long totalCompilationTime;

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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public Date getJvmStartTime() {
        return jvmStartTime;
    }

    public void setJvmStartTime(Date jvmStartTime) {
        this.jvmStartTime = jvmStartTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(String inputArguments) {
        this.inputArguments = inputArguments == null ? null : inputArguments.trim();
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch == null ? null : arch.trim();
    }

    public Integer getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(Integer availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName == null ? null : osName.trim();
    }

    public String getFileEncode() {
        return fileEncode;
    }

    public void setFileEncode(String fileEncode) {
        this.fileEncode = fileEncode == null ? null : fileEncode.trim();
    }

    public String getJvm() {
        return jvm;
    }

    public void setJvm(String jvm) {
        this.jvm = jvm == null ? null : jvm.trim();
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion == null ? null : javaVersion.trim();
    }

    public String getJavaSpecificationVersion() {
        return javaSpecificationVersion;
    }

    public void setJavaSpecificationVersion(String javaSpecificationVersion) {
        this.javaSpecificationVersion = javaSpecificationVersion == null ? null : javaSpecificationVersion.trim();
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome == null ? null : javaHome.trim();
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath == null ? null : javaLibraryPath.trim();
    }

    public Integer getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(Integer loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public Long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(Long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public Long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(Long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public Long getTotalCompilationTime() {
        return totalCompilationTime;
    }

    public void setTotalCompilationTime(Long totalCompilationTime) {
        this.totalCompilationTime = totalCompilationTime;
    }
}