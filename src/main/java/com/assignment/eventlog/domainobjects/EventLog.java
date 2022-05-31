package com.assignment.eventlog.domainobjects;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventLog {
    private static EventLog eventLog;

    private File logFile;

    private EventLog() {
    }

    public static EventLog getInstance() {
        if (eventLog == null) eventLog = new EventLog();
        return eventLog;
    }

}
