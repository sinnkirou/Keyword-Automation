package com.aaa.olb.automation.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.CellEntity;
import com.aaa.olb.automation.configuration.ConfigurationOptions;
import com.aaa.olb.automation.configuration.EnvironmentVariable;
import com.aaa.olb.automation.configuration.TestCaseEntity;

public class TestCaseProvider {
	
	/**
	 * get testcase entities from testcase sheets data
	 * 
	 * @param provider
	 * @return
	 */
	public static Map<String, TestCaseEntity> read(DataProvider provider){
		
		Map<String, TestCaseEntity> testcases = new HashMap<>();
		List<Map<String, CellEntity>> sources = provider.getData();

		for (Map<String, CellEntity> source : sources) {
			TestCaseEntity tc = new TestCaseEntity();
			EnvironmentVariable env = new EnvironmentVariable();
			for (String key : source.keySet()) {
				switch (key.toLowerCase()) {
				case ConfigurationOptions.ENV_OPTION_ID:
					tc.setTestCaseID(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_DESC:
					tc.setDescription(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_ENABLED:
					env.setEnabled(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_SITE_URL:
					env.setSiteURL(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_BROWSER:
					env.setBrowserType(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_REMOTE_HUB:
					env.setRemoteHub(source.get(key).getValue());
					break;
				case ConfigurationOptions.ENV_OPTION_HEADLESS:
					env.setHeadless(source.get(key).getValue().toLowerCase() == "true");
					break;
				default:
					break;
				}
			}
			if (env.getEnabled()) {
				tc.setEnvironmentVariable(env);
				testcases.put(tc.getTestCaseID(), tc);
			}
		}
		return testcases;
	}

}
