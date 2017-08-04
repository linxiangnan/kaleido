package com.lxn.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by linxiangnan on 2017/7/27.
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(String.format("任务执行！！！时间：[%s]。", new Date().toString()));

    }
}
