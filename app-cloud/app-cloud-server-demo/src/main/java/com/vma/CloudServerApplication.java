package com.vma;


import com.vma.cloud.EnableVmaCloud;
import com.vma.cloud.server.EnableVmaCloudServer;
import com.vma.core.EnableVmaCore;
import com.vma.core.VmaSpringApplication;
import com.vma.mybatis.EnableVmaMybatis;
import com.vma.redis.EnableVmaRedis;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot应用类
 *
 * @author hhd
 */
@SpringBootApplication
@EnableVmaCore
@EnableVmaRedis
@EnableVmaMybatis
@MapperScan("com.vma.**.dao")
@EnableVmaCloud
@EnableVmaCloudServer
@Slf4j
public class CloudServerApplication {

    public static void main(String[] args) {
        VmaSpringApplication.run(CloudServerApplication.class, args);
        log.info("app-cloud-server start completion");
    }
}



