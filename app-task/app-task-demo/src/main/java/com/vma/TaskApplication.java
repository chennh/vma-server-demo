package com.vma;


import com.vma.core.EnableVmaCore;
import com.vma.core.VmaSpringApplication;
import com.vma.mybatis.EnableVmaMybatis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Task启动
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@SpringBootApplication
@EnableScheduling
@EnableVmaCore
@EnableVmaMybatis
@MapperScan("com.vma.**.dao")
public class TaskApplication {
    public static void main(String[] args) {
        VmaSpringApplication.run(TaskApplication.class, args);
    }
}
