package com.assignment.eventlog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.eventlog.model.EventAlert;

@Repository
public interface EventAlertRepository extends CrudRepository<EventAlert, String> {
}
