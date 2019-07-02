package com.aaa.olb.automation.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;

import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.log.LoggerHelper;
import com.google.common.collect.ImmutableMap;

/**
 * @author ziv
 *
 * websocket protocol is not supported.
 */
public class MockNetworkUtils {

	/**
	 * @param webdriver
	 */
	@SuppressWarnings("rawtypes")
	public static void mockNetworkShutdown(WebDriver webdriver) {
		isSupportBrowser(webdriver);
		String message = "start mock NetworkShutdown";
		Log.info(message);
		System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
		
		//Set the conditions
		ChromeDriver driver = (ChromeDriver) webdriver;
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("offline", true);
		map.put("latency", 5);
		map.put("download_throughput", 5000);
		map.put("upload_throughput", 5000);

		CommandExecutor executor =  driver.getCommandExecutor();
		try {
			executor.execute(new Command(driver.getSessionId(),"setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param webdriver
	 */
	@SuppressWarnings("rawtypes")
	public static void mockNetworkRecover(WebDriver webdriver) {
		isSupportBrowser(webdriver);
		String message = "start mock NetworkRecover";
		Log.info(message);
		System.out.println(LoggerHelper.formatConsoleLog("INFO") + message);
		
		//Set the conditions
		ChromeDriver driver = (ChromeDriver) webdriver;
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("offline", false);
		map.put("latency", 5);
		map.put("download_throughput", 5000);
		map.put("upload_throughput", 5000);

		CommandExecutor executor =  driver.getCommandExecutor();
		try {
			executor.execute(new Command(driver.getSessionId(),"setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void isSupportBrowser(WebDriver webdriver) {
		if(webdriver instanceof ChromeDriver == false) {
			String error = "not supported browser, please use chrome.";
			Log.error(error);
			System.out.println(LoggerHelper.formatConsoleLog("ERROR") + error);
			throw new NotFoundException(error);
		}
	}
}
