package com.assignment.eventlog.service.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.eventlog.appconfig.ApplicationConfigData;
import com.assignment.eventlog.model.Event;
import com.assignment.eventlog.model.EventAlert;

@Component
public class EventCache {
	
	private Map<String, Event> cache;
	
	public void add(Event event) {
		getCache().put(event.getId(), event);
	}
	
	public Event get(String id) {
		return getCache().get(id);
	}
	
	public void clear() {
		getCache().clear();
	}
	
	public void evict(String id) {
		getCache().remove(id);
	}

	private Map<String, Event> getCache() {
		if (cache == null) {
			cache = new HashMap<String, Event>();
		}
		return cache;
	}
}
