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
	 * async operation or request timeout - seconds
	 */
	private long waitOrDealyTimeout;

	/**
	 * page jump or component jump timeout - seconds
	 */
	private long redirectTimeout;

	/**
	 * the retry count
	 */
	private int retry;
	
	/**
	 * should testcases parallel running
	 */
	private boolean parallel;
	
	private int threadCount;

	private RuntimeSettings() {
		this.timeout = 1;
		this.waitOrDealyTimeout = 3;
		this.redirectTimeout = 10;
		this.implicitTimeout = 5;
		this.explicitTimeout = 5;
		this.retry = 0;
		this.parallel = false;
		this.threadCount = 1;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getWaitOrDelayTimeout() {
		return waitOrDealyTimeout;
	}

	public long getRedirectTimeout() {
		return redirectTimeout;
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

	public void set(String name, String value) {
		switch (name.toLowerCase()) {
		case SystemConstants.SETTINGS_WAIT_TIME: {
			this.timeout = Long.valueOf(value);
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
		case SystemConstants.SETTINGS_PARALLEL: {
			this.parallel = value.toLowerCase().equals("true");
			break;
		}
		case SystemConstants.SETTINGS_THREAD_COUNT: {
			this.threadCount = Integer.valueOf(value) > 1 ? Integer.valueOf(value) : 1;
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

	/**
	 * @return the threadCount
	 */
	public int getThreadCount() {
		return threadCount;
	}

	/**
	 * @param threadCount the threadCount to set
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

}
