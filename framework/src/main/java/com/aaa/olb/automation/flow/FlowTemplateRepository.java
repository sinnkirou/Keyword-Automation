package com.aaa.olb.automation.flow;

import java.util.HashMap;
import java.util.Map;

public final class FlowTemplateRepository {

	private static FlowTemplateRepository _instance;

	static {
		_instance = new FlowTemplateRepository();
	}

	public static FlowTemplateRepository getInstance() {
		return _instance;
	}

	private Map<String, FlowTemplate> templates;

	private FlowTemplateRepository() {
		this.templates = new HashMap<>();
	}

	public void addTemplate(String name, FlowTemplate template) {
		this.templates.put(name, template);
	}

	public FlowTemplate getTemplate(String name) {
		return this.templates.get(name);
	}
}
