package com.aaa.olb.automation.testng;

import java.net.MalformedURLException;

import com.aaa.olb.automation.configuration.BrowserType;
import com.aaa.olb.automation.configuration.EnvironmentVariable;

public class BrowserFactory {

	public static Browser getBrowser(EnvironmentVariable env) throws MalformedURLException {

		String remoteHub = (env.getRemoteHub() != null && env.getRemoteHub().toUpperCase() != "N/A"
				&& env.getRemoteHub().toUpperCase() != "") ? env.getRemoteHub() : "N/A";
		switch (env.getBrowserType().toLowerCase()) {
		case BrowserType.FIREFOX:
			return new Firefox(remoteHub);
		case BrowserType.IE:
			return new IE(remoteHub);
		default:
			return new Chrome(remoteHub);
		}
	}

}
