package com.aaa.olb.automation.framework;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.aaa.olb.automation.configuration.PageModelEntity;
import com.aaa.olb.automation.log.Log;
import com.itranswarp.compiler.JavaStringCompiler;

/**
 * create a PageRepository which contains enabled pages,
 * 
 * each page is a hash map using page name as the key, class name as the value,
 * 
 * class name will be used to create page instance afterwards.
 * 
 * @author ziv
 *
 */
public class PageRepository {

	private static PageRepository _instance;

	static {
		_instance = new PageRepository();
	}

	private Map<String, Class<?>> pages;

	private PageRepository() {
		pages = new HashMap<>();
	}

	public static PageRepository getInstance() {
		return _instance;
	}

	public void addPage(String name, Class<?> page) {
		this.pages.put(name, page);
	}

	public Class<?> getPage(String name) {
		return this.pages.get(name);
	}

	/**
	 * get the instance of a page with provided webDriver and pageClazz, inside each
	 * page clazz constructor, PageFactory will create web page instance along with
	 * web elements instances
	 * 
	 * @param driver
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public static BasePage create(SearchContext driver, Class<?> page) throws Exception {
		Constructor<?> contructor = page.getConstructor(WebDriver.class);
		return (BasePage) contructor.newInstance(driver);
	}

	public Class<?> createClazz(String packageName, String pageName, List<PageModelEntity> targets) throws Exception {
		Class<?> clazz = null;
		JavaStringCompiler compiler = new JavaStringCompiler();

		String sourceCode = getSourceCode(packageName, pageName, targets);
		Log.info(sourceCode);
		System.out.println(sourceCode);
		try {
			Map<String, byte[]> results = compiler.compile(pageName + ".java", sourceCode);
			clazz = compiler.loadClass(packageName + "." + pageName, results);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.error(e.getLocalizedMessage());
			throw e;
		}
		return clazz;
	}

	private static String getTargetMethodString(PageModelEntity e) {
		StringBuilder sb = new StringBuilder();
		sb.append("	@" + e.getFindBy() + "(\"" + e.getFindByValue() + "\")\n");
		sb.append("	private " + e.getFiledType() + " " + e.getTargetName().toLowerCase() + ";\n");
		sb.append("	@ColumnName(value = \"" + e.getTargetName() + "\", shouldWait = " + e.getShouldWait()
				+ ", shouldDelay = " + e.getShouldDelay() + ", blur = " + e.getShouldBlur() + ")\n");
		sb.append("	public " + e.getFiledType() + " get" + e.getTargetName() + "() {\n");
		sb.append("		return " + e.getTargetName().toLowerCase() + ";\n");
		sb.append("	}\n");
		return sb.toString();
	}

	private static String getSourceCode(String packageName, String pageName, List<PageModelEntity> targets) {
		StringBuilder sb = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		for (PageModelEntity e : targets) {
			methods.append(getTargetMethodString(e));
		}
		sb.append("package " + packageName + ";\n");
		sb.append("import java.util.List;\n");
		sb.append("import org.openqa.selenium.WebDriver;\n");
		sb.append("import com.aaa.olb.automation.annotations.*;\n");
		sb.append("import com.aaa.olb.automation.controls.*;\n");
		sb.append("import com.aaa.olb.automation.framework.BasePage;\n");
		sb.append("import com.aaa.olb.automation.components.*;\n");
		sb.append("public class " + pageName + " extends BasePage {\n");
		sb.append("	public " + pageName + "(WebDriver driver) {\n");
		sb.append("		super(driver);\n");
		sb.append("	}\n");
		sb.append(methods.toString());
		sb.append("}\n");
		return sb.toString();
	}

}
