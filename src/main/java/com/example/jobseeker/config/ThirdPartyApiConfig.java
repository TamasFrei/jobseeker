package com.example.jobseeker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "thirdpartyapi")
@Getter
@Setter
public class ThirdPartyApiConfig {

    private String apiKey;
    private String url;
}
