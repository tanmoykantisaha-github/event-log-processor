package com.assignment.eventlog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.eventlog.domainobjects.EventLog;
import com.assignment.eventlog.validator.EventLogValidator;

@Service
public class EventLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogService.class);

    @Autowired
    private EventLogValidator validator;

    @Autowired
    private EventLogProcessor processor;

    public void service(String... args) {
        EventLog eventLog = EventLog.getInstance();
        validator.validateInput(eventLog, args);
        processor.process(eventLog);
    }

}
