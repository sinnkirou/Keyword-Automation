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
	private long waitOrDealyTimeout;

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
	
	private boolean parallel = false;

	private RuntimeSettings() {
		this.timeout = 5;
		this.shortTimeout = 5;
		this.longTimeout = 10;
		this.operationTimeout = 5;
		this.waitOrDealyTimeout = 3;
		this.redirectTimeout = 10;
		this.implicitTimeout = 10;
		this.explicitTimeout = 10;
		this.sleepTime = 3;
		this.retry = 0;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getOperationTimeout() {
		return operationTimeout;
	}

	public long getWaitOrDelayTimeout() {
		return waitOrDealyTimeout;
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

	public void set(String name, String value) {
		switch (name.toLowerCase()) {
		case SystemConstants.SETTINGS_WAIT_TIME: {
			this.timeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_LONG_WAIT_TIME: {
			this.longTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_SHORT_WAIT_TIME: {
			this.shortTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_OPERATION_WAIT_TIME: {
			this.operationTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_REDIRECT_WAIT_TIME: {
			this.redirectTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_WAIT_OR_DELAY_TIME: {
			this.waitOrDealyTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_RETRY: {
			this.retry = Integer.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_EXPLICIT_WAIT_TIME: {
			this.explicitTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_IMPLICIT_WAIT_TIME: {
			this.implicitTimeout = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_SLEEP_TIME: {
			this.sleepTime = Long.valueOf(value);
			break;
		}
		case SystemConstants.SETTINGS_PARALLEL: {
			this.parallel = value.toLowerCase().equals("true");
			break;
		}
		default: {
			break;
		}
		}
	}

	/**
	 * @return the parallel
	 */
	public boolean isParallel() {
		return parallel;
	}

	/**
	 * @param parallel the parallel to set
	 */
	public void setParallel(boolean parallel) {
		this.parallel = parallel;
	}

}
