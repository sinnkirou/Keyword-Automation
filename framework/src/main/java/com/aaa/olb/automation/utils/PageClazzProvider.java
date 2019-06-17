package com.aaa.olb.automation.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.aaa.olb.automation.configuration.PageModelEntity;
import com.aaa.olb.automation.log.Log;
import com.itranswarp.compiler.JavaStringCompiler;

public class PageClazzProvider {
	/**
	 * get the page class with corresponding page name
	 * 
	 * @param pageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getPageClazz(String packageName, String pageName) throws ClassNotFoundException {
		return Class.forName(packageName + "." + pageName);
	}

	public static Class<?> createClazz(String packageName, String pageName, List<PageModelEntity> targets)
			throws Exception {
		Class<?> clazz = null;
		JavaStringCompiler compiler = new JavaStringCompiler();

		String sourceCode = getSourceCode(packageName, pageName, targets);
		// Log.info(sourceCode);
		// System.out.println(sourceCode);
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
		sb.append(String.format("	@%s(\"%s\")\n", e.getFindBy(), e.getFindByValue()));
		sb.append(String.format("	private %s %s;\n", e.getFiledType(), e.getTargetName().toLowerCase()));
		sb.append(String.format("	@ColumnName(value = \"%s\", shouldWait = %s, shouldDelay = %s, blur = %s)\n",
				e.getTargetName(), e.getShouldWait(), e.getShouldDelay(), e.getShouldBlur()));
		sb.append(String.format("	public %s get%s() {\n", e.getFiledType(), e.getTargetName()));
		sb.append(String.format("		return %s;\n", e.getTargetName().toLowerCase()));
		sb.append("	}\n");
		return sb.toString();
	}

	private static String getSourceCode(String packageName, String pageName, List<PageModelEntity> targets) {
		StringBuilder sb = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		for (PageModelEntity e : targets) {
			methods.append(getTargetMethodString(e));
		}
		sb.append(String.format("package %s;\n", packageName));
		sb.append("import java.util.List;\n");
		sb.append("import org.openqa.selenium.WebDriver;\n");
		sb.append("import com.aaa.olb.automation.annotations.*;\n");
		sb.append("import com.aaa.olb.automation.controls.*;\n");
		sb.append("import com.aaa.olb.automation.framework.BasePage;\n");
		sb.append("import com.aaa.olb.automation.components.*;\n");
		sb.append(String.format("public class %s extends BasePage {\n", pageName));
		sb.append(String.format("	public %s(WebDriver driver) {\n", pageName));
		sb.append("		super(driver);\n");
		sb.append("	}\n");
		sb.append(methods);
		sb.append("}\n");
		return sb.toString();
	}
}
