package com.vma;

import com.vma.core.EnableVmaCore;
import com.vma.freemarker.EnableVmaFreemarker;
import com.vma.web.EnableVmaWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/8/5.
 */
@SpringBootApplication
@EnableVmaCore
@EnableVmaWeb
@EnableVmaFreemarker
@Slf4j
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.info("app-web start completion");
    }
}
