package com.digisign.pdf;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.digisign.pdf")
@EnableAutoConfiguration
@SpringBootApplication

public class DownloadProjectApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DownloadProjectApplication.class, args);
        BasicConfigurator.configure();
//        PropertyConfigurator.configure("log4j.properties");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // TODO Auto-generated method stub
        return builder.sources(DownloadProjectApplication.class);
    }
}
