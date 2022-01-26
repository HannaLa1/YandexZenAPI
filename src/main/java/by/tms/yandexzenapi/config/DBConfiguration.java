package by.tms.yandexzenapi.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Slf4j
@Getter
@Setter
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection(){
        log.info("DB connection for DEV - H2, driver-class-name: {}, url : {}", driverClassName, url);
        return "DB connection for DEV - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection(){
        log.info("DB connection for PROD - MySQL, driver-class-name: {}, url : {}", driverClassName, url);
        return "DB connection for PROD - MySQL";
    }
}
