package com.lxn.quartz.scheduler;

import com.lxn.quartz.job.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linxiangnan on 2017/7/27.
 * function    定时器
 */
@Service
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    @Transactional
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, CronExpression cronExpression) {

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(jobName, jobGroupName).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(cronScheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName){

        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

        try {
            // 暂停触发器
            scheduler.pauseTrigger(triggerKey);
            // 删除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除job任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
