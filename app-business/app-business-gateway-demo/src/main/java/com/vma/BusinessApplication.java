package com.vma;


import com.vma.authorization.EnableVmaAuthorization;
import com.vma.core.EnableVmaCore;
import com.vma.core.VmaSpringApplication;
import com.vma.mybatis.EnableVmaMybatis;
import com.vma.redis.EnableVmaRedis;
import com.vma.resource.EnableVmaResource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@SpringBootApplication
@EnableVmaCore
@EnableVmaRedis
@EnableVmaMybatis
@EnableVmaAuthorization
@EnableVmaResource
@MapperScan("com.vma.**.dao")
@Slf4j
public class BusinessApplication {

    public static void main(String[] args) {
        VmaSpringApplication.run(BusinessApplication.class, args);
        log.info("app-business start completion");
    }

}



