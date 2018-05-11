package com.fang.material;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@EnableCaching
public class Material extends SpringBootServletInitializer {
    private static final Log log = LogFactory.getLog(Material.class);

    public static void main(String[] args) {
        log.info("====================================================================================================");
        log.info("Starting Material Server...");
        SpringApplication.run(Material.class, args);
        log.info("Material Server started!");
        log.info("====================================================================================================");
    }
}
