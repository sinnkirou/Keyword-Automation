package com.aaa.olb.automation.configuration;

public class PageModelEntity {
	private String findBy = "XPath";
	private String findByValue;
	private String targetName;
	private Boolean shouldWait = false;
	private Boolean shouldDelay = false;
	private Boolean shouldBlur = false;
	private String filedType;
	
	/**
	 * @return the findBy
	 */
	public String getFindBy() {
		return findBy;
	}
	/**
	 * @param findBy the findBy to set
	 */
	public void setFindBy(String findBy) {
		this.findBy = findBy;
	}
	
	/**
	 * @return the findByValue
	 */
	public String getFindByValue() {
		return findByValue;
	}
	/**
	 * @param findByValue the findByValue to set
	 */
	public void setFindByValue(String findByValue) {
		this.findByValue = findByValue;
	}
	
	/**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * @param targetName the targetName to set
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	/**
	 * @return the shouldWait
	 */
	public Boolean getShouldWait() {
		return shouldWait;
	}
	/**
	 * @param shouldWait the shouldWait to set
	 */
	public void setShouldWait(Boolean shouldWait) {
		this.shouldWait = shouldWait;
	}
	
	/**
	 * @return the shouldDelay
	 */
	public Boolean getShouldDelay() {
		return shouldDelay;
	}
	/**
	 * @param shouldDelay the shouldDelay to set
	 */
	public void setShouldDelay(Boolean shouldDelay) {
		this.shouldDelay = shouldDelay;
	}
	
	/**
	 * @return the shouldBlur
	 */
	public Boolean getShouldBlur() {
		return shouldBlur;
	}
	/**
	 * @param shouldBlur the shouldBlur to set
	 */
	public void setShouldBlur(Boolean shouldBlur) {
		this.shouldBlur = shouldBlur;
	}
	/**
	 * @return the filedType
	 */
	public String getFiledType() {
		return filedType;
	}
	/**
	 * @param filedType the filedType to set
	 */
	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}
}
