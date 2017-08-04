package com.lxn.quartz.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linxiangnan on 2017/7/27.
 * function     index
 */
@RestController
public class IndexController {

    @Autowired
    private Logger logger;

    @RequestMapping("/")
    public Map index() {

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "启动完成，success。");
        return result;
    }

//    @RequestMapping(value = "/test/add_job", method = RequestMethod.POST)
//    public Map testAddJob(@RequestBody Map request){
//
//        String jobName = request.get("jobName").toString();
//        String jobGroupName = request.get("jobGroupName").toString();
//        String triggerName = request.get("triggerName").toString();
//        String triggerGroupName = request.get("triggerGroupName").toString();
//        String cronExpressionStr = request.get("cronExpressionStr").toString();
//
//        CronExpression cronExpression = null;
//        try {
//            cronExpression = new CronExpression(cronExpressionStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        quartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, cronExpression);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 0);
//        result.put("message", "定时任务添加成功。");
//        return result;
//    }
//
//    @RequestMapping(value = "/test/delete_job", method = RequestMethod.DELETE)
//    public Map testDeleteJob(@RequestBody Map request){
//
//        String jobName = request.get("jobName").toString();
//        String jobGroupName = request.get("jobGroupName").toString();
//        String triggerName = request.get("triggerName").toString();
//        String triggerGroupName = request.get("triggerGroupName").toString();
//
//        quartzManager.deleteJob(jobName, jobGroupName, triggerName, triggerGroupName);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 0);
//        result.put("message", "定时任务删除成功。");
//        return result;
//    }


}
