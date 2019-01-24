package com.aaa.olb.automation.configuration;

public class RuntimeSettings {

	private static RuntimeSettings _instance;

	static {
		_instance = new RuntimeSettings();
	}

	public static RuntimeSettings getInstance() {
		return _instance;
	}

	/**
	 * implicit time out -seconds
	 */
	private long implicitTimeout;

	/**
	 * explicit time out -seconds
	 */
	private long explicitTimeout;

	/**
	 * default timeout - seconds
	 */
	private long timeout;

	/**
	 * control operation timeout - seconds
	 */
	private long operationTimeout;

	/**
	 * async operation or request timeout - seconds
	 */
	private long asyncTimeout;

	/**
	 * page jump or component jump timeout - seconds
	 */
	private long redirectTimeout;

	/**
	 * max timeout - seconds
	 */
	private long longTimeout;

	/**
	 * min timeout - seconds
	 */
	private long shortTimeout;

	/**
	 * the retry count
	 */
	private int retry;

	private long sleepTime;

	private RuntimeSettings() {
		this.timeout = 10;
		this.shortTimeout = 5;
		this.longTimeout = 60;
		this.operationTimeout = 15;
		this.asyncTimeout = 30;
		this.redirectTimeout = 300;
		this.implicitTimeout = 30;
		this.explicitTimeout = 60;
		this.sleepTime = 3;
		this.retry = 0;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getOperationTimeout() {
		return operationTimeout;
	}

	public long getAsyncTimeout() {
		return asyncTimeout;
	}

	public long getRedirectTimeout() {
		return redirectTimeout;
	}

	public long getLongTimeout() {
		return longTimeout;
	}

	public long getShortTimeout() {
		return shortTimeout;
	}

	public int getRetry() {
		return retry;
	}

	public long getImplicitTimeout() {
		return implicitTimeout;
	}

	public void setImplicitTimeout(long implicitTimeout) {
		this.implicitTimeout = implicitTimeout;
	}

	public long getExplicitTimeout() {
		return explicitTimeout;
	}

	public void setExplicitTimeout(long explicitTimeout) {
		this.explicitTimeout = explicitTimeout;
	}

	public long getSleepTime() {
		return sleepTime * 1000;
	}

	public void set(String name, int value) {
		switch (name.toLowerCase()) {
		case SystemConstants.SETTINGS_WAIT_TIME: {
			this.timeout = value;
			break;
		}
		case SystemConstants.SETTINGS_LONG_WAIT_TIME: {
			this.longTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_SHORT_WAIT_TIME: {
			this.shortTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_OPERATION_WAIT_TIME: {
			this.operationTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_REDIRECT_WAIT_TIME: {
			this.redirectTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_ASYNC_WAIT_TIME: {
			this.asyncTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_RETRY: {
			this.retry = value;
			break;
		}
		case SystemConstants.SETTINGS_EXPLICIT_WAIT_TIME: {
			this.explicitTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_IMPLICIT_WAIT_TIME: {
			this.implicitTimeout = value;
			break;
		}
		case SystemConstants.SETTINGS_SLEEP_TIME: {
			this.sleepTime = value;
			break;
		}
		default: {
			break;
		}
		}
	}

}
