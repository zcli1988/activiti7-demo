package org.activiti.examples;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.activiti.examples.context.HttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        HttpMessageConverter httpConverter = new HttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        httpConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(httpConverter);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
