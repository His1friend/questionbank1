package org.homework.questions_bank.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        java.util.Properties properties = new java.util.Properties();
        properties.put("kaptcha.image.width", "200");
        properties.put("kaptcha.image.height", "50");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.length", "5");
        properties.put("kaptcha.textproducer.font.size", "40");
        properties.put("kaptcha.textproducer.char.space", "4");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
