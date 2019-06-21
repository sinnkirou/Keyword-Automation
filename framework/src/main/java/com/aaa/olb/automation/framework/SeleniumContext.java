package com.aaa.olb.automation.framework;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.configuration.EnvironmentVariable;
import com.aaa.olb.automation.log.ActionRepository;

/**
 * global variable for selenium environment
 *
 */
public class SeleniumContext {

	protected WebDriver driver;

	protected SearchContext parent;

	protected Route route;

	protected ActionRepository repository;

	protected EnvironmentVariable env;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public ActionRepository getRepository() {
		return repository;
	}

	public void setRepository(ActionRepository repository) {
		this.repository = repository;
	}

	public SearchContext getParent() {
		return parent;
	}

	public void setParent(SearchContext parent) {
		this.parent = parent;
	}

	public EnvironmentVariable getEnv() {
		return env;
	}

	public void setEnv(EnvironmentVariable env) {
		this.env = env;
	}

}
