package org.homework.questions_bank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class QuestionsBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionsBankApplication.class, args);
    }

}
