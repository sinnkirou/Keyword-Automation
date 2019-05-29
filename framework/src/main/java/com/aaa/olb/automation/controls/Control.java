package com.aaa.olb.automation.controls;

import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * click control
	 * 
	 * @throws Exception
	 * 
	 */
	public void click() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.moveToElement(we).click().perform();
		this.info(this, generateAction("click", startTime, LocalDateTime.now()));
	}

	public void clickByJS() throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", we);
		} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException ex) {
			throw ex;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * double click control
	 * 
	 * @throws Exception
	 * 
	 */
	public void doubleClick() {
		LocalDateTime startTime = LocalDateTime.now();
		scrollToViewElement();
		Actions actions = new Actions(this.driver);
		actions.doubleClick();
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
	 * 鼠标拖拽动作
	 * 将 source 元素拖放到 target 元素的位置
	 * */
	public void dragAndDrop(WebElement target) {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(driver); 
		action.dragAndDrop(we, target);
		this.info(this, generateAction("dragAndDrop", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标拖拽动作
	 * 将 source 元素拖放到 (xOffset, yOffset) 位置，其中 xOffset 为横坐标，yOffset 为纵坐标。
	 * */
	public void dragAndDropByOffset(String text) {
		LocalDateTime startTime = LocalDateTime.now();
		int xOffset = Integer.parseInt(text.split(",")[0]);
		int yOffset = text.split(",").length > 1 ? Integer.parseInt(text.split(",")[1]) : 0;
		Actions action = new Actions(driver); 
		action.dragAndDropBy(we, xOffset, yOffset);
		this.info(this, generateAction("dragAndDropBy", startTime, LocalDateTime.now()));
	}
	
	/*
	 * 鼠标悬停操作
	 * */
	public void clickAndHold() {
		LocalDateTime startTime = LocalDateTime.now();
		Actions action = new Actions(driver); 
		action.clickAndHold(we);
		this.info(this, generateAction("clickAndHold", startTime, LocalDateTime.now()));
	}
}
