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
		System.out.println(sourceCode);
		Map<String, byte[]> results;
		try {
			results = compiler.compile(pageName + ".java", sourceCode);
			clazz = compiler.loadClass(packageName + "." + pageName, results);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.error(e.getLocalizedMessage());
			throw e;
		}
		return clazz;
	}

	private static String getTargetMethodString(PageModelEntity e) {
		return "	@" + e.getFindBy() + "(\"" + e.getFindByValue() + "\")\n" + "	private GenericControl "
				+ e.getTargetName().toLowerCase() + ";\n" + "	@ColumnName(\"" + e.getTargetName() + "\")\n"
				+ "	public GenericControl get" + e.getTargetName() + "() {\n" + "		return "
				+ e.getTargetName().toLowerCase() + ";\n" + "	}\n";
	}

	private static String getSourceCode(String packageName, String pageName, List<PageModelEntity> targets) {
		StringBuilder sb = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		for (PageModelEntity e : targets) {
			methods.append(getTargetMethodString(e));
		}
		sb.append("package " + packageName + ";\n" + "import java.util.List;\n"
				+ "import org.openqa.selenium.WebDriver;\n" + "import com.aaa.olb.automation.annotations.*;\n"
				+ "import com.aaa.olb.automation.controls.GenericControl;\n"
				+ "import com.aaa.olb.automation.framework.BasePage;\n" + "public class " + pageName
				+ " extends BasePage {\n" + "	public " + pageName + "(WebDriver driver) {\n"
				+ "		super(driver);\n" + "	}\n" + methods.toString() + "}\n");
		return sb.toString();
	}

}
