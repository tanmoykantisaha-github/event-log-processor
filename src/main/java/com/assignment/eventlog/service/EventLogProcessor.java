package com.assignment.eventlog.service;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.assignment.eventlog.appconfig.ApplicationConfigData;
import com.assignment.eventlog.model.Event;
import com.assignment.eventlog.model.EventAlert;
import com.assignment.eventlog.model.EventLog;
import com.assignment.eventlog.service.cache.EventAlertCache;
import com.assignment.eventlog.service.cache.EventCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventLogProcessor {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventLogProcessor.class);
	
	private EventAlertCache eventAlertCache;
	
	private EventCache eventCache;
	
	private ApplicationConfigData appConfigData;
	
	public EventLogProcessor(EventAlertCache eventAlertCache, EventCache eventCache,
			ApplicationConfigData appConfigData) {
		super();
		this.eventAlertCache = eventAlertCache;
		this.eventCache = eventCache;
		this.appConfigData = appConfigData;
	}


	public void process(EventLog eventLog) {
        try (LineIterator lineIterator = FileUtils.lineIterator(eventLog.getLogFile())) {
            while (lineIterator.hasNext()) {
                Event event;
                try {
                    event = new ObjectMapper().readValue(lineIterator.nextLine(), Event.class);

                    if (eventCache.get(event.getId()) != null) {
                        Event cachedEvent = eventCache.get(event.getId());
                        int executionTime = calculateExecutionTime(event, cachedEvent);

                        EventAlert alert = new EventAlert(event, executionTime);

                        if (executionTime > appConfigData.getAlertThresholdMs()) {
                            alert.setAlert(Boolean.TRUE);
                        }

                        eventAlertCache.add(alert);

                        eventCache.evict(event.getId());
                    } else {
                    	eventCache.add(event);
                    }
                } catch (JsonProcessingException e) {
                    LOGGER.error("Unable to parse the event! {}", e.getMessage());
                }
            }
            eventAlertCache.persist();
        } catch (IOException e) {
            LOGGER.error("Error processing the log file : {} : {}", eventLog.getLogFile().getAbsolutePath(), e.getMessage());
        }
    }


	private int calculateExecutionTime(Event event, Event cachedEvent) {
		return Math.abs(Math.toIntExact(event.getTimestamp() - cachedEvent.getTimestamp()));
	}

}
