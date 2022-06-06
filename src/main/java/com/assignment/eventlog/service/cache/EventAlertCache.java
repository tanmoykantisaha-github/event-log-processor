package com.assignment.eventlog.service.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.assignment.eventlog.appconfig.ApplicationConfigData;
import com.assignment.eventlog.dao.EventAlertRepository;
import com.assignment.eventlog.model.EventAlert;

@Component
public class EventAlertCache {
	
	private Map<String, EventAlert> cache;
	
	private ApplicationConfigData appConfigData;
	
	private EventAlertRepository repo;
	
	public EventAlertCache(ApplicationConfigData appConfigData, EventAlertRepository repo) {
		super();
		this.appConfigData = appConfigData;
		this.repo = repo;
	}

	public void add(EventAlert eventAlert) {
		getCache().put(eventAlert.getId(), eventAlert);
		if (getCache().size() == appConfigData.getRecordPersistLimit()) {
			persist();
		}
	}
	
	public void persist() {
		repo.saveAll(getCache().values());
		clear();
	}
	
	public void clear() {
		getCache().clear();
	}
	
	public void evict(String id) {
		getCache().remove(id);
	}

	private Map<String, EventAlert> getCache() {
		if (cache == null) {
			cache = new HashMap<String, EventAlert>();
		}
		return cache;
	}
}
