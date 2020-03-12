package com.daurada;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.event.LoggingEvent;

import ch.qos.logback.core.AppenderBase;

public class TestAppender extends AppenderBase<LoggingEvent> {
	
    public static List<LoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(LoggingEvent e) {
        events.add(e);
    }
}
