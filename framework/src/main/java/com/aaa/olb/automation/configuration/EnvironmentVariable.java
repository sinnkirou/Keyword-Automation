package com.aaa.olb.automation.configuration;

public class EnvironmentVariable {

	private String browserType;

	private String siteURL;

	private String remoteHub;

	private Boolean enabled = false;

	private Boolean headless = false;

	private int priority = 100;

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

	/**
	 * @return the headless
	 */
	public Boolean getHeadless() {
		return headless;
	}

	/**
	 * @param headless the headless to set
	 */
	public void setHeadless(Boolean headless) {
		this.headless = headless;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
