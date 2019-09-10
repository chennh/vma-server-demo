package com.vma;


import com.vma.cloud.EnableVmaCloud;
import com.vma.cloud.client.EnableVmaCloudClient;
import com.vma.core.VmaSpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot应用类
 *
 * @author hhd
 */
@SpringBootApplication
@EnableVmaCloud
@EnableVmaCloudClient
@Slf4j
public class CloudClientApplication {

    public static void main(String[] args) {
        VmaSpringApplication.run(CloudClientApplication.class, args);
        log.info("app-cloud-client start completion");
    }
}



