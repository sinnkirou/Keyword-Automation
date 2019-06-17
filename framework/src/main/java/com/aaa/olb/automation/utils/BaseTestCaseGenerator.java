package com.aaa.olb.automation.utils;

import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.TestCaseEntity;
import com.aaa.olb.automation.configuration.TestStepEntity;
import com.aaa.olb.automation.flow.FlowDeclaration;

public interface BaseTestCaseGenerator {

	Map<String, TestCaseEntity> getTestCases(String filePath) throws Exception;

	List<TestStepEntity> getTestStepEntities(List<FlowDeclaration> flows, String filePath) throws Exception;
}
