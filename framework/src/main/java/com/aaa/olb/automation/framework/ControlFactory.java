package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

/**
 * 1.使用了proxy的方式,在实例化WebElement的时候，实际上不管WebElement存在不存在都可以创建
 * 而实际findElement都会延迟到真的调用这个元素时执行, 这带来一个好处就是如果WebElement的实例创建后
 * 页面DOM刷新后,需要重新查找WebElement,否则可能抛出StaleElementReferenceException,
 * 而Proxy之后每次都会自动 查找,这样就减少了代码处理
 * 2.工厂的设计模式同时也带了了灵活程度,在创建页面或者页面元素的时候,可以开始添加统一的前置或者后置的处理
 *
 */
public class ControlFactory {

	public Object create(SeleniumContext context) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		WebElement element = this.find(context);
		Class<?> targetType = (Class<?>) context.getRoute().getFieldType();
		Constructor<?> constructor = targetType.getConstructor(SeleniumContext.class, WebElement.class);
		return constructor.newInstance(context, element);
	}

	/**
	 * Proxy.newProxyInstance()方法有三个参数： 1. 类加载器(Class Loader) 2. 需要实现的接口数组 3.
	 * InvocationHandler接口。所有动态代理类的方法调用，都会交由InvocationHandler接口实现类里的invoke()方法去处理。这是动态代理的关键所在。
	 * 
	 * @param context
	 * @return
	 */
	private WebElement find(SeleniumContext context) {
		InvocationHandler handler = new LocatingElementHandler(new DefaultElementLocator(context));
		return (WebElement) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[] { WebElement.class, WrapsElement.class, Locatable.class }, handler);
	}

}
