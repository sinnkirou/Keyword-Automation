package com.aaa.olb.automation.listeners;

public class RetryCounter {
	private static RetryCounter _instance;

	static {
		_instance = new RetryCounter();
	}

	private int count;

	private RetryCounter() {
		count = 0;
	}

	public static RetryCounter getInstance() {
		return _instance;
	}

	public int getCounter() {
		return count;
	}
	
	public int setCounter(int count) {
		return this.count = count;
	}

	public void reset() {
		this.count = 0;
	}
}
