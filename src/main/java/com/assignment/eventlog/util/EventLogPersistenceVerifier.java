package com.assignment.eventlog.util;

import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.eventlog.dao.EventAlertRepository;

@Component
public class EventLogPersistenceVerifier {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventLogPersistenceVerifier.class);
	
	@Autowired
	EventAlertRepository repo;
	
	public void verify() {
		StreamSupport.stream(repo.findAll().spliterator(), false)
				.map((alert) -> alert.toString())
				.forEach((alertJSON) -> LOGGER.info(alertJSON));
	}

}
