package org.training.issuetracker.controllers;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class TestListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
		super.contextInitialized(event);
	//	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}




}
