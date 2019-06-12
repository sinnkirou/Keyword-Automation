package com.aaa.olb.automation.flow;

public class FlowDeclaration {

	private String name;
	
	private boolean template;
	
	private String page;
	
	private boolean excelModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the excelModel
	 */
	public boolean isExcelModel() {
		return excelModel;
	}

	/**
	 * @param excelModel the excelModel to set
	 */
	public void setExcelModel(boolean excelModel) {
		this.excelModel = excelModel;
	}
}
