package com.aaa.olb.automation.configuration;

public class EnvironmentVariable {
	
	private String browserType;
	
	private String siteURL;
	
	private String remoteHub;
	
	private Boolean enabled = false;

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}
	
	public String getRemoteHub() {
		return remoteHub;
	}
	
	public void setRemoteHub(String remoteHub) {
		this.remoteHub = remoteHub;
	}
	
	public RuntimeSettings getSettings() {
		return RuntimeSettings.getInstance();
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled.toLowerCase().equals("true") ? true : false;
	}

}
