package com.assignment.eventlog.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.assignment.eventlog.domainobjects.EventLog;

@Component
public class EventLogValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogValidator.class);

    public void validateInput(EventLog eventLog, String... args) {
        LOGGER.info("Validation started");

        validateArguments(args);
        validateFilePath(eventLog, args[0]);
        
        LOGGER.info("Validation completed");

    }

    private void validateFilePath(EventLog eventLog, String logFilePath) {
        LOGGER.info("Log file to be processed: {}", logFilePath);
        
        try {
            File file = new ClassPathResource("logfiles/" + logFilePath).getFile();
            if (!file.exists()) {
                file = new ClassPathResource(logFilePath).getFile();
                if (!file.exists()) {
                    file = new File(logFilePath);
                }
            }

            if (!file.exists())
                throw new FileNotFoundException("No such log file to be processed : " + logFilePath);
            
            eventLog.setLogFile(file);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void validateArguments(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Please provide path to log file to be processed.");
        }
    }
}
