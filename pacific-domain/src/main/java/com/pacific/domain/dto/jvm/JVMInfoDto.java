package com.pacific.domain.dto.jvm;

import java.util.Date;

/**
 * Created by Fe on 16/7/11.
 */
public class JVMInfoDto {

    private Date startTime;

    private String hostname;

    private String jVM;

    private String javaVersion;

    private String pID;

    private String inputArguments;

    private String javaHome;

    private String arch;

    private String oSName;

    private String oSVersion;

    private String javaSpecificationVersion;

    private String javaLibraryPath;

    private String fileEncode;

    private int availableProcessors;

    private int loadedClassCount;

    private long totalLoadedClassCount;

    private long unloadedClassCount;

    private long totalCompilationTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(String inputArguments) {
        this.inputArguments = inputArguments;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getJavaSpecificationVersion() {
        return javaSpecificationVersion;
    }

    public void setJavaSpecificationVersion(String javaSpecificationVersion) {
        this.javaSpecificationVersion = javaSpecificationVersion;
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public String getFileEncode() {
        return fileEncode;
    }

    public void setFileEncode(String fileEncode) {
        this.fileEncode = fileEncode;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public long getTotalCompilationTime() {
        return totalCompilationTime;
    }

    public void setTotalCompilationTime(long totalCompilationTime) {
        this.totalCompilationTime = totalCompilationTime;
    }


    public String getjVM() {
        return jVM;
    }

    public void setjVM(String jVM) {
        this.jVM = jVM;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getoSName() {
        return oSName;
    }

    public void setoSName(String oSName) {
        this.oSName = oSName;
    }

    public String getoSVersion() {
        return oSVersion;
    }

    public void setoSVersion(String oSVersion) {
        this.oSVersion = oSVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
