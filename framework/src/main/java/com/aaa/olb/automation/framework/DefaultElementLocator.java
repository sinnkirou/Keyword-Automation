package com.aaa.olb.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import java.util.List;

/**
 * The default element locator, which will lazily locate an element or an
 * element list on a page. This class is designed for use with the
 * {@link org.openqa.selenium.support.PageFactory} and understands the
 * annotations {@link org.openqa.selenium.support.FindBy} and
 * {@link org.openqa.selenium.support.CacheLookup}.
 */
public class DefaultElementLocator implements ElementLocator {
	private SeleniumContext context;
	private final By by;

	public DefaultElementLocator(SeleniumContext context) {
		this.context = context;
		this.by = this.context.getRoute().getFinder();
	}

	/**
	 * Find the element.
	 */
	public WebElement findElement() {
		return this.context.getParent().findElement(this.by);
	}

	/**
	 * Find the element list.
	 */
	public List<WebElement> findElements() {
		return this.context.getParent().findElements(by);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " '" + by + "'";
	}
}
