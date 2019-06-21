package com.aaa.olb.automation.customizedFlow;

import java.lang.reflect.InvocationTargetException;

import com.aaa.olb.automation.flow.BasicFlowTemplate;
import com.aaa.olb.automation.flow.FlowDeclaration;
import com.aaa.olb.automation.flow.FlowTemplate;
import com.aaa.olb.automation.flow.FlowTemplateRepository;
import com.aaa.olb.automation.util.Constants;

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

			/*
			 * a option is to add the template manually, which will provide the customized
			 * template here more clearly
			 * 
			 * if not, will then use class.forName with flow name to dynamically add the
			 * template by default
			 */
			case FlowNames.SEARCHING:
				FlowTemplateRepository.getInstance().addTemplate(flow.getName(), new SearchingTemplate());
				break;

			case FlowNames.LOGIN:
				FlowTemplateRepository.getInstance().addTemplate(flow.getName(), new LoginTemplate());
				break;

			default:
				try {
					FlowTemplate template = (FlowTemplate) Class
							.forName(Constants.CUSTOMIZED_FLOW_PACKAGE_NAME + "." + flow.getName() + "Template")
							.getConstructor().newInstance();
					FlowTemplateRepository.getInstance().addTemplate(flow.getName(), template);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// Log.info(e.getLocalizedMessage());
				}
				break;
			}
		}

	}

	public static FlowTemplate getFlowTemplate(String templateName) {
		FlowTemplate template = FlowTemplateRepository.getInstance().getTemplate(templateName);

		return template != null ? template : new BasicFlowTemplate();
	}
}
