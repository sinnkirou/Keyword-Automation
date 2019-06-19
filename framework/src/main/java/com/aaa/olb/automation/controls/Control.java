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

import com.aaa.olb.automation.annotations.BehaviorIndication;
import com.aaa.olb.automation.configuration.RuntimeSettings;
import com.aaa.olb.automation.configuration.SystemConstants;
import com.aaa.olb.automation.framework.ControlCollectionFactory;
import com.aaa.olb.automation.framework.ControlFactory;
import com.aaa.olb.automation.framework.LocationKind;
import com.aaa.olb.automation.framework.Route;
import com.aaa.olb.automation.framework.SeleniumContext;
import com.aaa.olb.automation.log.ActionRepository;
import com.aaa.olb.automation.log.BaseAction;
import com.aaa.olb.automation.log.Log;
import com.aaa.olb.automation.utils.ParameterExacter;

@BehaviorIndication(name = SystemConstants.BEHAVIOR_CLICK, provider = SystemConstants.DEFAULT_BEHAVIOR_PROVIDER_CLASS)
public class Control extends ActionRepository {
	protected WebElement we;

	protected WebDriver driver;

	protected SeleniumContext context;

	/**
	 * @param context
	 * @param webElement
	 */
	protected Control(SeleniumContext context, WebElement webElement) {
		super();
		this.context = context;
		this.driver = this.context.getDriver();
		we = webElement;
	}

	/**
	 * @param actionName
	 * @param startTime
	 * @param endTime
	 * @return action
	 */
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
	 * @return True- visible, False- not
	 */
	public boolean visible() {
		try {
			this.threadSleep();
			LocalDateTime startTime = LocalDateTime.now();
			boolean value = we.isDisplayed();
			this.info(this, generateAction(String.format("check isVisible"), startTime, LocalDateTime.now()));
			return value;
		} catch (NoSuchElementException | StaleElementReferenceException | NullPointerException ex) {
			// ex.printStackTrace();
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * The control is enabled or not
	 * 
	 * @return True- enabled, False- not
	 */
	public boolean enabled() {
		try {
			this.threadSleep();
			LocalDateTime startTime = LocalDateTime.now();
			boolean value = we.isEnabled();
			this.info(this, generateAction(String.format("isEnabled"), startTime, LocalDateTime.now()));
			return value;
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * @param cssName: style attribute name
	 * @return css style value: string
	 */
	public String getCssValue(String cssName) {
		LocalDateTime startTime = LocalDateTime.now();
		String value = we.getCssValue(cssName);
		this.info(this, generateAction(String.format("getCssValue"), startTime, LocalDateTime.now()));
		return value;
	};

	/**
	 * Get control's all applied css class
	 * 
	 * @return all css class names: string
	 */
	public String getClassName() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = we.getAttribute("class");
		this.info(this, generateAction(String.format("getClassName"), startTime, LocalDateTime.now()));
		return value;
	};

	/**
	 * Get control's element tag
	 * 
	 * @return tag name: string
	 */
	public String getTagName() {
		LocalDateTime startTime = LocalDateTime.now();
		String value = we.getTagName();
		this.info(this, generateAction(String.format("getTagName"), startTime, LocalDateTime.now()));
		return value;
	}

	/**
	 * Get control's one attribute
	 * 
	 * @param name: string - attribute name
	 * @return attribute value: string
	 */
	public String getAttribute(String name) {
		LocalDateTime startTime = LocalDateTime.now();
		String value = we.getAttribute(name);
		this.info(this, generateAction(String.format("getAttribute"), startTime, LocalDateTime.now()));
		return value;
	}

	/**
	 * Wait for control display *
	 */
	public void waitForVisible() {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();
		if (!this.visible()) {
			try {
				wait.until(ExpectedConditions.visibilityOf(we));
			} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
				Log.error(ex.getLocalizedMessage());
				throw ex;
			} catch (TimeoutException ex) {
				Log.error(ex.getLocalizedMessage());
				throw ex;
			}
		}
		this.info(this, generateAction("wait for visible", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control display
	 * 
	 * @param seconds
	 */
	public void waitForVisible(long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
		if (!this.visible()) {
			try {
				this.threadSleep();
				wait.until(ExpectedConditions.visibilityOf(we));
			} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
				Log.error(ex.getLocalizedMessage());
				throw ex;
			} catch (TimeoutException ex) {
				Log.error(ex.getLocalizedMessage());
				throw ex;
			}
		}
		this.info(this, generateAction("wait for visible", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control being clickable
	 */
	public void waitForClickable() {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this, generateAction("wait for clickable", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control being clickable
	 * 
	 * @param seconds
	 */
	public void waitForClickable(long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(we));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this, generateAction("wait for clickable", startTime, LocalDateTime.now()));
	}

	/**
	 * Wait for control disappear
	 * 
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
	 * Wait for control disappear
	 * 
	 * @param seconds
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

	/**
	 * wait until control's attribute with specific value exits
	 * 
	 * @param attribute
	 * @param value
	 */
	public void waitForSpecificAttribute(String attribute, String value) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBe(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * wait until control's attribute with specific value exits
	 * 
	 * @param attribute
	 * @param value
	 * @param seconds
	 */
	public void waitForSpecificAttribute(String attribute, String value, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBe(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * wait until control's attribute contained with specific value exits
	 * 
	 * @param attribute
	 * @param value
	 * @param seconds
	 */
	public void waitForAttributeContained(String attribute, String value, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeContains(we, attribute, value));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * wait until control's attribute contained with specific value exits
	 * 
	 * @param attribute
	 * @param value
	 */
	public void waitForNotEmptyAttribute(String attribute) {
		WebDriverWait wait = new WebDriverWait(this.driver, RuntimeSettings.getInstance().getOperationTimeout());
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBeNotEmpty(we, attribute));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * wait until control's attribute not empty
	 * 
	 * @param attribute
	 * @param seconds
	 */
	public void waitForNotEmptyAttribute(String attribute, long seconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, seconds);
		LocalDateTime startTime = LocalDateTime.now();

		try {
			wait.until(ExpectedConditions.attributeToBeNotEmpty(we, attribute));
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
		this.info(this,
				generateAction(String.format("wait for attribute - %s", attribute), startTime, LocalDateTime.now()));
	}

	/**
	 * @Param Route
	 * 
	 *        Route route = new Route(); route.setFieldType(Span.class);
	 *        route.setLocationKind(LocationKind.XPATH);
	 *        route.setLocation(".//span[contains(text(), '"+ text +"')]");
	 *        route.setFieldName("span"); Span target = getChildren(route);
	 * 
	 * @return return child control instance: Control
	 */
	@SuppressWarnings("unchecked")
	public <T extends Control> T getChildren(Route route) throws Exception {
		SeleniumContext context = new SeleniumContext();
		context.setDriver(this.context.getDriver());
		context.setParent(this.we);
		context.setRoute(route);
		ControlFactory factory = new ControlFactory();
		return (T) factory.create(context);
	}

	/**
	 * @param Route
	 * 
	 *              for details, see definition for getChildren
	 * 
	 * @return return children control instances: Control list
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
	 * 
	 * 调用方法为 element.scrollIntoView() 参数默认为true。
	 * 
	 * 参数为true时调用该函数，页面（或容器）发生滚动，使element的顶部与视图（容器）顶部对齐；
	 * 
	 * 参数为false时，使element的底部与视图（容器）底部对齐。
	 * 
	 * TIPS：页面（容器）可滚动时才有用！
	 */
	public void scrollToViewElement() {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(false);", we);
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		} catch (TimeoutException ex) {
			Log.error(ex.getLocalizedMessage());
			throw ex;
		}
	}

	/**
	 * click control with mouse
	 * 
	 */
	public void click() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.moveToElement(we).click().perform();
		this.info(this, generateAction("click", startTime, LocalDateTime.now()));
	}

	/**
	 * click control by JS
	 * 
	 * @throws Exception
	 */
	public void clickByJS() {
		LocalDateTime startTime = LocalDateTime.now();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", we);
		this.info(this, generateAction("clickByJS", startTime, LocalDateTime.now()));
	}

	/**
	 * context-click control with mouse
	 * 
	 */
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
		actions.moveToElement(we).click().build().perform();
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		//executor.executeScript("arguments[0].focus();", we);
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
	 * get Parent Control
	 * 
	 * @param <T>
	 * @param type
	 * @return Control
	 * @throws Exception
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
	 * get WebElement
	 * 
	 */
	public WebElement getWebElement() {
		return we;
	}

	/**
	 * 鼠标拖拽元素动作 将 we 拖放到 target 元素的位置
	 * 
	 * @param target
	 */
	public void dragAndDrop(WebElement target) {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.moveToElement(we).dragAndDrop(we, target).perform();
		;
		this.info(this, generateAction("dragAndDrop", startTime, LocalDateTime.now()));
	}

	/**
	 * 鼠标拖拽元素动作 将 we 拖放到 (xOffset, yOffset) 位置，其中 xOffset 为横坐标，yOffset 为纵坐标。
	 * 
	 * @param text e.g.: text -> "xOffset, yOffset"
	 */
	public void dragAndDropByOffset(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = ParameterExacter.getIntParameters(text, 2);
		Actions action = new Actions(this.driver);
		action.moveToElement(we).dragAndDropBy(we, parameters[0], parameters[1]).perform();
		this.info(this, generateAction("dragAndDropBy with offset: " + text, startTime, LocalDateTime.now()));
	}

	/**
	 * 鼠标在当前位置拖拽,拖动到相对于当前元素的(xOffset, yOffset) 位置
	 * 
	 * @param text e.g.: text -> "xOffset, yOffset"
	 */
	public void dragAndDropByOffsetFromCurrent(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = ParameterExacter.getIntParameters(text, 2);
		Actions action = new Actions(this.driver);
		action.clickAndHold().moveToElement(we, parameters[0], parameters[1]).perform();
		action.release().perform();
		this.info(this,
				generateAction("dragAndDropByOffsetAtCurrent with offset: " + text, startTime, LocalDateTime.now()));
	}

	/**
	 * 鼠标悬停操作
	 */
	public void clickAndHold() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.moveToElement(we).clickAndHold(we).perform();
		this.info(this, generateAction("clickAndHold", startTime, LocalDateTime.now()));
	}

	/**
	 * 将鼠标移到元素中点
	 */
	public void moveToElement() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.moveToElement(we).perform();
		this.info(this, generateAction("moveToElement", startTime, LocalDateTime.now()));
	}

	/**
	 * 以鼠标当前位置或者 (0,0) 为中心开始移动到 (xOffset, yOffset) 坐标轴
	 * 
	 * @param text e.g.: text -> "xOffset, yOffset"
	 */
	public void moveByOffsetFromStart(String text) {
		Actions action = new Actions(this.driver);
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = ParameterExacter.getIntParameters(text, 2);
		action.moveByOffset(parameters[0], parameters[1]).perform();
		this.info(this, generateAction("moveByOffsetFromStart with offset: " + text, startTime, LocalDateTime.now()));
	}

	/**
	 * 将鼠标移到当前元素 的 (xOffset, yOffset) 位置， 这里的 (xOffset, yOffset) 是以当前元素的左上角为 (0,0)
	 * 开始的 (x, y) 坐标轴。
	 * 
	 * @param text e.g.: text -> "xOffset, yOffset"
	 */
	public void moveByOffsetFromElement(String text) {
		Actions action = new Actions(this.driver);
		LocalDateTime startTime = LocalDateTime.now();
		int[] parameters = ParameterExacter.getIntParameters(text, 2);
		action.moveToElement(we, parameters[0], parameters[1]).perform();
		this.info(this, generateAction("moveByOffsetFromElement with offset: " + text, startTime, LocalDateTime.now()));
	}

	/**
	 * 在当前停留的位置做单击操作，鼠标左键
	 * 
	 */
	public void clickAtCurrentPosition() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.click().perform();
		this.info(this, generateAction("clickAtCurrentPosition", startTime, LocalDateTime.now()));
	}

	/**
	 * 在当前停留的位置做右击操作，鼠标右键
	 * 
	 */
	public void rightClickAtCurrentPosition() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.contextClick().perform();
		this.info(this, generateAction("rightClickAtCurrentPosition", startTime, LocalDateTime.now()));
	}

	/**
	 * 释放鼠标
	 * 
	 */
	public void release() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(this.driver);
		action.release().perform();
		this.info(this, generateAction("release", startTime, LocalDateTime.now()));
	}

	/**
	 * sendKeys键盘模拟, 通过selenium原生方法
	 * 
	 * @param keyname
	 */
	public void sendKey(String keyname) {
		try {
			if(Keys.valueOf(keyname) != null) {
				LocalDateTime startTime = LocalDateTime.now();
				CharSequence cs = Keys.valueOf(keyname);
				Actions action = new Actions(this.driver);
				action.moveToElement(we).sendKeys(cs).perform();
				this.info(this, generateAction("sendKey: " + keyname, startTime, LocalDateTime.now()));
			}
		}catch(Exception e) {
			sendKeyByRobot(keyname);
		}
	}

	/**
	 * Robot模拟键盘模拟 字母键 a、b、c、d … z， 一些符号键比如：‘ {}\[] ’、‘ \ ’、‘。’、‘ ? ’、‘：’、‘ + ’、‘ -
	 * ’、‘ = ’、、‘“”’， 还有一些不常用到的功能键如 PrtSc、ScrLk/NmLk
	 * 
	 * @param keyname
	 */
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
			Log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * sleep for specific time
	 * 
	 * default time from WaitOrDelayTimeout from RuntimeSettings instance
	 */
	public void threadSleep() {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			long time = RuntimeSettings.getInstance().getWaitOrDelayTimeout() * 1000;
			Thread.sleep(time);
			this.info(this, generateAction("threadSleep: " + time + " milliseconds", startTime, LocalDateTime.now()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}
	
	/**
	 * sleep for specific minutes
	 * 
	 */
	public void threadSleepByMinutes(String parameter) {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			double minutes = Double.parseDouble(parameter);
			long time = new Double(minutes * 60 * 1000).longValue();
			Thread.sleep(time);
			this.info(this, generateAction("threadSleepByMinutes: " + parameter, startTime, LocalDateTime.now()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	/*
	public void sendCompositeKey(String parameters) {
		String[] keynames = parameters.split(",");
		try {
			if(Keys.valueOf(keynames[0]) != null) {
				LocalDateTime startTime = LocalDateTime.now();
				CharSequence cs1 = Keys.valueOf(keynames[0]);
				Actions action = new Actions(this.driver);
				action.moveToElement(we).keyDown(cs1).sendKeys(keynames[1]).perform();
				this.info(this, generateAction("sendCompositeKey: " + parameters, startTime, LocalDateTime.now()));
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	*/
}
