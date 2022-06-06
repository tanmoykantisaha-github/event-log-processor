package com.assignment.eventlog.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event_alerts")
public class EventAlert {
    @Id
    private String id;

    private int duration;

    private EventType type;

    private String host;

    private Boolean alert;

    public EventAlert() {
    }

    public EventAlert(Event event, int duration) {
        this.id = event.getId();
        this.type = event.getType();
        this.host = event.getHost();
        this.duration = duration;
        this.alert = Boolean.FALSE;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
