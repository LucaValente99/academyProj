package com.luca.academyProj.businesscomponent.model;

import org.springframework.context.ApplicationEvent;

public class FailedLoginAttemptEvent extends ApplicationEvent {
	private static final long serialVersionUID = -903596079348848994L;
	
	private final String username;

    public FailedLoginAttemptEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
