package com.assignment.eventlog.domainobjects;

import java.util.Arrays;

public enum EventState {
	STARTED("STARTED"), FINISHED("FINISHED");

	private final String value;

	EventState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static EventState fromValue(String text) {
		return Arrays.stream(values()).filter(v -> v.getValue().equals(text)).findFirst().orElse(null);
	}
}
