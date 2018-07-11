package com.alvaro.udemy.springboot.jpa.demo;

import com.alvaro.udemy.springboot.jpa.demo.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    @Autowired
    UploadFileService uploadFileService;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        uploadFileService.deleteAll();
        uploadFileService.init();
    }
}
