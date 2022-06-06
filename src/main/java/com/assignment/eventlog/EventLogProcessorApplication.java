package com.assignment.eventlog;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assignment.eventlog.service.EventLogService;

@SpringBootApplication
public class EventLogProcessorApplication implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(EventLogProcessorApplication.class);

    private EventLogService service;
    
    public EventLogProcessorApplication(EventLogService service) {
		super();
		this.service = service;
	}

	public static void main(String... args) {
        SpringApplication app = new SpringApplication(EventLogProcessorApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) {
    	Instant startTime = Instant.now();
    	LOGGER.info("Start time : {}", startTime);
        service.service(args);
        Instant endTime = Instant.now();
        LOGGER.info("End time : {}", endTime);
        LOGGER.info("Time elasped: {}ms", Duration.between(startTime, endTime).toMillis());
    }

}
