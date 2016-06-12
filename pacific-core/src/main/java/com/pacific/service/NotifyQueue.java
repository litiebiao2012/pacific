package com.pacific.service;

import com.pacific.domain.entity.AlarmLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Fe on 16/6/12.
 */
public class NotifyQueue {

    public static final Logger logger = LoggerFactory.getLogger(NotifyQueue.class);

    public static LinkedBlockingQueue<AlarmLog> alarmLogBlockingQueue = new LinkedBlockingQueue<AlarmLog>();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    AlarmLog alarmLog = alarmLogBlockingQueue.peek();
                    if (alarmLog == null) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.error("InterruptedException e : {}",e);
                        }
                    } else {

                    }
                }

            }
        }).start();
    }

    public static void putAlarmLog(AlarmLog alarmLog) {
        alarmLogBlockingQueue.add(alarmLog);
    }



}
