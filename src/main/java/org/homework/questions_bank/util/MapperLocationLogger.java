package org.homework.questions_bank.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MapperLocationLogger {

    @Value("${mybatis.mapper-locations:classpath:mapper/*.xml}")
    private String mapperLocations;

    @PostConstruct
    public void logMapperLocations() {
        System.out.println("MyBatis mapper locations: " + mapperLocations);
    }
}
