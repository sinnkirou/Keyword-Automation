package com.aaa.olb.automation.controls;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.LocalDateTime;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.framework.ControlCollectionFactory;
import com.aaa.olb.automation.framework.ControlFactory;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.ActionRepository;
import com.aaa.olb.automation.log.BaseAction;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.PatameterExacter;

public class Control extends ActionRepository {
	protected WebElement we;

	protected WebDriver driver;

	protected SeleniumContext context;

	protected Control(SeleniumContext context, WebElement webElement) {
		super();
		this.context = context;
		this.driver = this.context.getDriver();
		we = webElement;
	}

	protected BaseAction generateAction(String actionName, LocalDateTime startTime, LocalDateTime endTime) {
		String targetName = this.getClass().getName();
		if (this.context != null && this.context.getRoute() != null) {
			targetName = this.context.getRoute().getFieldName();
		}
		BaseAction action = new BaseAction();
		action.setTarget(this);
		action.setTargetName(targetName);
		action.setAction(actionName);
		action.setStartTime(startTime);
		action.setEndTime(endTime);
		action.setCompleted(true);
		return action;
	}

	/**
	 * The control is visible or not
	 * 
	 * return: True- visible, False- not
	 */
	public boolean visible() {
		try {
			return we.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException | NullPointerException ex) {
			// ex.printStackTrace();
			return false;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * The control is enabled or not
	 * 
	 * return: True- enabled, False- not
	 */
	public boolean enabled() {
		try {
			we.isEnabled();
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		return we.isEnabled();
	}

	/**
	 * Get control's one css style value. Parameters: cssName: string - css style
	 * attribute name
	 * 
	 * return css style value: string
	 * 
	 */
	public String getCssValue(String cssName) {
		return we.getCssValue(cssName);
	};

	/**
	 * Get control's all applied css class
	 * 
	 * return all css class names: string
	 * 
	 */
	public String getClassName() {
		return we.getAttribute("class");
	};

	/**
	 * Get control's element tag
	 * 
	 * return tag name: string
	 * 
	 */
	public String getTagName() {
		return we.getTagName();
	}

	/**
	 * Get control's one attribute
	 * 
	 * Parameters: name: string - attribute name
	 * 
	 * return attribute value: string
	 * 
	 */
	public String getAttribute(String name) {
		return we.getAttribute(name);
	}

	/**
	 * Wait for control display *
	 */
	public void waitForVisible() {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();
//		if (!this.visible())
			try {
				wait.until(ExpectedConditions.visibilityOf(we));
			} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
				throw ex;
			} catch (TimeoutException ex) {
				throw ex;
			}
		this.info(this, generateAction("wait for visible", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control display *
	 */
	public void waitForVisible(long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
//		if (!this.visible())
			try {
				wait.until(ExpectedConditions.visibilityOf(we));
			} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
				throw ex;
			} catch (TimeoutException ex) {
				throw ex;
			}
		this.info(this, generateAction("wait for visible", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control being clickable *
	 */
	public void waitForClickable() {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this, generateAction("wait for clickable", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control being clickable *
	 */
	public void waitForClickable(long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this, generateAction("wait for clickable", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control disappear *
	 */
	public void waitForHidden() {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();
		if (this.visible()) {
			try {
				wait.until(ExpectedConditions.invisibilityOf(we));
			} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException ex) {
				ex.printStackTrace();
			}
		}

		this.info(this, generateAction("wait for hidden", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control disappear *
	 */
	public void waitForHidden(long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
		if (this.visible()) {
			try {
				wait.until(ExpectedConditions.invisibilityOf(we));
			} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException ex) {
				ex.printStackTrace();
			}
		}

		this.info(this, generateAction("wait for hidden", startTime, LocalDateTime.now()));
	}

	public void waitForSpecificAttribute(String attribute, String value) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBe(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	public void waitForSpecificAttribute(String attribute, String value, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBe(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	public void waitToPresent(String attribute, String value) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeContains(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	public void waitForAttributeContained(String attribute, String value, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeContains(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	public void waitForNotEmptyAttribute(String attribute) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBeNotEmpty(we, attribute));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	public void waitForNotEmptyAttribute(String attribute, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBeNotEmpty(we, attribute));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * Get child銆�
	 * 
	 * Parameters: locator: selenium.by - element locator type: control- child
	 * control type.
	 * 
	 * return child control instance: Control
	 * 
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T extends Control> T getChildren(Route route) throws Exception {
		// WebElement element = we.findElement(locator);
		// Constructor<?> constructor = type.getConstructor(SeleniumContext.class,
		// WebElement.class);
		// @SuppressWarnings("unchecked")
		// SeleniumContext context =new SeleniumContext();
		// context.setDriver(this.context.getDriver());
		// T target = (T) constructor.newInstance(context, element);
		// context.setRepository((ActionRepository)target);
		// return target;
		SeleniumContext context = new SeleniumContext();
		context.setDriver(this.context.getDriver());
		context.setParent(this.we);
		context.setRoute(route);
		ControlFactory factory = new ControlFactory();
		return (T) factory.create(context);
	}

	/**
	 * Get children with same type銆�
	 * 
	 * Parameters: locator: selenium.by - element locator type: control- child
	 * control type.
	 * 
	 * return children control instances: Control list
	 */
	@SuppressWarnings("unchecked")
	public <T extends Control> List<T> getChildrens(Route route) throws Exception {
		SeleniumContext context = new SeleniumContext();
		context.setDriver(this.context.getDriver());
		context.setParent(this.we);
		context.setRoute(route);
		ControlCollectionFactory factory = new ControlCollectionFactory();
		return (List<T>) factory.create(context);
	}

	/**
	 * scroll to view element
	 * 
	 * @throws Exception
	 * 
	 */
	public void scrollToViewElement() {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(false);", we);
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getCause().getMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getCause().getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * click control
	 * 
	 */
	public void click() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.moveToElement(we).click().perform();
		this.info(this, generateAction("click", startTime, LocalDateTime.now()));
	}

	public void clickByJS() {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", we);
			this.info(this, generateAction("clickByJS", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getCause().getMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getCause().getMessage());
			ex.printStackTrace();
		}
	}
	
	public void rightClick() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).contextClick().perform();
		this.info(this, generateAction("rightClick", startTime, LocalDateTime.now()));
	}

	/**
	 * double click control
	 * 
	 */
	public void doubleClick() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.moveToElement(we).doubleClick().perform();
		this.info(this, generateAction("doubleClick", startTime, LocalDateTime.now()));
	}

	/**
	 * focus control
	 * 
	 */
	public void focus() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.moveToElement(we).click().perform();
		this.info(this, generateAction("focus", startTime, LocalDateTime.now()));
	}

	/**
	 * hover control
	 * 
	 */
	public void hover() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions action = new Actions(this.driver);
		action.moveToElement(we).build().perform();
		this.info(this, generateAction("hover", startTime, LocalDateTime.now()));
	}

	/**
	 * get parent
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T extends Control> T getParent(Class<T> type) throws Exception {
		SeleniumContext context = new SeleniumContext();
		context.setDriver(this.context.getDriver());
		context.setParent(this.we);
		Route route = new Route();
		route.setFieldType(type);
		route.setFieldName(String.format("%s's parent", this.context.getRoute().getFieldName()));
		route.setLocation("./..");
		route.setLocationKind(LocationKind.XPATH);
		context.setRoute(route);
		ControlFactory factory = new ControlFactory();
		T target = (T) factory.create(context);
		return target;
	}

	/**
	 * 
	 * get WebElement
	 * 
	 */
	public WebElement getWebElement() {
		return we;
	}
	
	/*
	 * 鼠标拖拽元素动作
	 * 将 source 元素拖放到 target 元素的位置
	 * */
	public void dragAndDrop(WebElement target) {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).dragAndDrop(we, target).perform();;
		this.info(this, generateAction("dragAndDrop", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标拖拽元素动作
	 * 将 source 元素拖放到 (xOffset, yOffset) 位置，其中 xOffset 为横坐标，yOffset 为纵坐标。
	 * */
	public void dragAndDropByOffset(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = PatameterExacter.getIntParameters(text, 2);
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).dragAndDropBy(we, parameters[0], parameters[1]).perform();
		this.info(this, generateAction("dragAndDropBy with offset: "+ text, startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标在当前位置拖拽,到当前元素的(xOffset, yOffset) 位置
	 * */
	public void dragAndDropByOffsetFromCurrent(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = PatameterExacter.getIntParameters(text, 2);
		Actions action = new Actions(this.driver); 
		action.clickAndHold().moveToElement(we, parameters[0], parameters[1]).perform(); 
		action.release().perform();
		this.info(this, generateAction("dragAndDropByOffsetAtCurrent with offset: "+ text, startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标悬停操作
	 * */
	public void clickAndHold() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).clickAndHold(we).perform();
		this.info(this, generateAction("clickAndHold", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 将鼠标移到元素中点
	 * */
	public void moveToElement() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).perform();
		this.info(this, generateAction("moveToElement", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 以鼠标当前位置或者 (0,0) 为中心开始移动到 (xOffset, yOffset) 坐标轴
	 * */
	public void moveByOffsetFromStart(String text) {
		Actions action = new Actions(this.driver); 
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = PatameterExacter.getIntParameters(text, 2);
		action.moveByOffset(parameters[0], parameters[1]).perform();
		this.info(this, generateAction("moveByOffsetFromStart with offset: "+text, startTime, LocalDateTime.now()));
	}
	
	/*
	 *  将鼠标移到元素 toElement 的 (xOffset, yOffset) 位置，
	 *  这里的 (xOffset, yOffset) 是以元素 toElement 的左上角为 (0,0) 开始的 (x, y) 坐标轴。
	 * */
	public void moveByOffsetFromElement(String text) {
		Actions action = new Actions(this.driver); 
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = PatameterExacter.getIntParameters(text, 2);
		action.moveToElement(we, parameters[0], parameters[1]).perform();
		this.info(this, generateAction("moveByOffsetFromElement with offset: "+ text, startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标左键在当前停留的位置做单击操作 
	 * */
	public void clickAtCurrentPosition() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.click().perform();
		this.info(this, generateAction("clickAtCurrentPosition", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标左键在当前停留的位置做右击操作 
	 * */
	public void rightClickAtCurrentPosition() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.contextClick().perform();
		this.info(this, generateAction("rightClickAtCurrentPosition", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 释放鼠标
	 * */
	public void release() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver); 
		action.release().perform();
		this.info(this, generateAction("release", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 键盘模拟
	 * */
	public void sendKey(String keyname) {
		LocalDateTime startTime = LocalDateTime.now();
		CharSequence cs = Keys.valueOf(keyname) != null ? Keys.valueOf(keyname) : keyname;
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).sendKeys(cs).perform();
		this.info(this, generateAction("sendKey: " + keyname, startTime, LocalDateTime.now()));
	}
	
	/*
	 * Robot模拟键盘模拟
	 * 字母键 a、b、c、d … z，
	 * 一些符号键比如：‘ {}\[] ’、‘ \ ’、‘。’、‘ ? ’、‘：’、‘ + ’、‘ - ’、‘ = ’、、‘“”’，
	 * 还有一些不常用到的功能键如 PrtSc、ScrLk/NmLk
	 * */
	@SuppressWarnings({ "restriction", "deprecation" })
	public void sendKeyByRobot(String keyname) { 
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver); 
		action.moveToElement(we).build().perform();
		Robot robot = null;
		try {
			robot = new Robot();
			int code = javafx.scene.input.KeyCode.valueOf(keyname).impl_getCode();
			robot.keyPress(code);
			robot.keyRelease(code);
			this.info(this, generateAction("sendKeyByRobot: " + keyname, startTime, LocalDateTime.now()));
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getCause().getMessage());
		} 
	 }
	
	/*
	 * @Param
	 * eg：text-> '50，60'
	 * select partial context from a span or div, eg: <div>test context</div>
	 */
	public void selectPartialContextByIndex(String text) {
		this.focus();
		String[] parameters = PatameterExacter.getParamters(text, 2);
		String start = !parameters[0].isEmpty() ? parameters[0] : "0";
		String end = !parameters[1].isEmpty() ? parameters[1] : String.valueOf(we.getText().length());
		StringBuilder script = new StringBuilder();
		script.append("function getTextNodesIn(node) {\n");
		script.append("  if (!node) {\n");
		script.append("    return;\n");
		script.append("  }\n");
		script.append("  const textNodes = [];\n");
		script.append("  if (node.nodeType === 3) {\n");
		script.append("    textNodes.push(node);\n");
		script.append("  } else {\n");
		script.append("    const children = node.childNodes;\n");
		script.append("    for (let i = 0, len = children.length; i < len; i += 1) {\n");
		script.append("      textNodes.push(...getTextNodesIn(children[i]));\n");
		script.append("    }\n");
		script.append("  }\n");
		script.append("  return textNodes;\n");
		script.append("}\n");
		script.append("\n");
		script.append("function setSelectionRange(el, start, end) {\n");
		script.append("  if (document.createRange && window.getSelection) {\n");
		script.append("    const range = document.createRange();\n");
		script.append("    range.selectNodeContents(el);\n");
		script.append("    const textNodes = getTextNodesIn(el);\n");
		script.append("    let foundStart = false;\n");
		script.append("    let charCount = 0;\n");
		script.append("    let endCharCount;\n");
		script.append("\n");
		script.append("    // eslint-disable-next-line\n");
		script.append("    for (let i = 0, textNode; (textNode = textNodes[i]); i += 1) {\n");
		script.append("      endCharCount = charCount + textNode.length;\n");
		script.append("      if (\n");
		script.append("        !foundStart &&\n");
		script.append("        start >= charCount &&\n");
		script.append("        (start < endCharCount || (start === endCharCount && i <= textNodes.length))\n");
		script.append("      ) {\n");
		script.append("        range.setStart(textNode, start - charCount);\n");
		script.append("        foundStart = true;\n");
		script.append("      }\n");
		script.append("      if (foundStart && end <= endCharCount) {\n");
		script.append("        range.setEnd(textNode, Math.abs(end - charCount));\n");
		script.append("        break;\n");
		script.append("      }\n");
		script.append("      charCount = endCharCount;\n");
		script.append("    }\n");
		script.append("\n");
		script.append("    const sel = window.getSelection();\n");
		script.append("    sel.removeAllRanges();\n");
		script.append("    sel.addRange(range);\n");
		script.append("  } else if (document.selection && document.body.createTextRange) {\n");
		script.append("    const textRange = document.body.createTextRange();\n");
		script.append("    textRange.moveToElementText(el);\n");
		script.append("    textRange.collapse(true);\n");
		script.append("    textRange.moveEnd('character', end);\n");
		script.append("    textRange.moveStart('character', start);\n");
		script.append("    textRange.select();\n");
		script.append("  }\n");
		script.append("}");
		script.append("setSelectionRange(arguments[0], "+start+", "+ end +");");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContextByIndex", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getCause().getMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getCause().getMessage());
			ex.printStackTrace();
		}
	}

	/*
	 *  @Param
	 * eg：text-> 'some context'
	 * select partial context from a span or div, eg: <div>test context</div>
	 */
	public void selectPartialContextByContext(String text) {
		this.focus();
		StringBuilder script = new StringBuilder();
		script.append("var start = arguments[0].textContent.indexOf('" + text + "');");
		script.append("var end = start + " + text.length() + ";");
		script.append("var leafNode = arguments[0].firstChild;");
		script.append("var range = document.createRange();");
		script.append("range.setStart(leafNode, start);");
		script.append("range.setEnd(leafNode, end);");
		script.append("var sel = window.getSelection();");
		script.append("sel.removeAllRanges();");
		script.append("sel.addRange(range);");
		try {
			LocalDateTime startTime = LocalDateTime.now();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript(script.toString(), we);
			this.info(this, generateAction("selectPartialContextByContext", startTime, LocalDateTime.now()));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getCause().getMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getCause().getMessage());
			ex.printStackTrace();
		}
	}
	
}
