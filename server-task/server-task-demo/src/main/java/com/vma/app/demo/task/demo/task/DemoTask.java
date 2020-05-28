package com.vma.app.demo.task.demo.task;

import cn.hutool.core.date.DateUtil;
import com.vma.app.demo.task.demo.service.IDemoService;
import com.vma.task.annotion.VmaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@Component
public class DemoTask {

    @Autowired
    private IDemoService demoService;

    /**
     * 定时器任务  需要使用@VmaTask来标记
     * task
     */
    @VmaTask(describe = "更新数据")
    @Scheduled(cron = "0/5 * * * * ? ")
    public void task() {
        System.out.println("定时任务-----------:" + DateUtil.now());
    }


}
