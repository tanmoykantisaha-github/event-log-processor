package com.assignment.eventlog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.eventlog.model.EventLog;
import com.assignment.eventlog.validator.EventLogValidator;

@Service
public class EventLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogService.class);

    private EventLogValidator validator;

    private EventLogProcessor processor;
    
    public EventLogService(EventLogValidator validator, EventLogProcessor processor) {
		super();
		this.validator = validator;
		this.processor = processor;
	}

	public void service(String... args) {
        EventLog eventLog = EventLog.getInstance();
        validator.validateInput(eventLog, args);
        processor.process(eventLog);
    }

}
