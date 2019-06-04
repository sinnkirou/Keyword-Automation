package com.aaa.olb.automation.customizedFlow;

import com.aaa.olb.automation.flow.BasicFlowTemplate;
import com.aaa.olb.automation.flow.FlowDeclaration;
import com.aaa.olb.automation.flow.FlowTemplate;
import com.aaa.olb.automation.flow.FlowTemplateRepository;

/**
 * if there is a new template created, 
 * 
 * make sure append a case to add the template to the template repository
 *
 */
public class TemplateProvider {
	public static void addTemplate(FlowDeclaration flow) {
		if (flow.isTemplate()) {
			switch (flow.getName()) {
			
			case FlowNames.SEARCHING:
				FlowTemplateRepository.getInstance().addTemplate(flow.getName(), new SearchingTemplate());
				break;
			
			default:
				break;
			}
		}

	}

	public static FlowTemplate getFlowTemplate(String templateName) {
		FlowTemplate template = FlowTemplateRepository.getInstance().getTemplate(templateName);

		return template != null ? template : new BasicFlowTemplate();
	}
}
