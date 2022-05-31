package com.assignment.eventlog.appconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("app.log-analyser")
public class ApplicationConfigData {
    private int alertThresholdMs = 4;
    private int recordPersistLimit = 1000;
    private int readLineLimit = 100;
    private int threadPoolSize = 10;
    

}
