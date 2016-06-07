package com.pacific.common.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Fe on 16/6/1.
 */
public class NamedThreadFactory implements ThreadFactory{

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    private String mPrefix;

    private boolean mDaemon;

    private ThreadGroup mGroup;

    public NamedThreadFactory() { this("pool-" + POOL_SEQ.getAndIncrement(),false);}

    public NamedThreadFactory(String name) {
        this(name,false);
    }

    public NamedThreadFactory(String mPrefix, boolean mDaemon) {
        this.mPrefix= mPrefix + "-thread-";
        this.mDaemon = mDaemon;
        SecurityManager s = System.getSecurityManager();
        this.mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = mPrefix + mThreadNum.getAndIncrement();
        Thread thread = new Thread(mGroup,r,name,0);
        thread.setDaemon(mDaemon);
        return thread;
    }

}
