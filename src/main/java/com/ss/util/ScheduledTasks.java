package com.ss.util;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时备份数据库功能
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Scheduled(cron = "0 0 1 ? * *")
    public void reportCurrentByCron(){
        DataBaseBackUp.backup();
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " +new Date());
    }
}
