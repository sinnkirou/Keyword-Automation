package com.aaa.olb.automation.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.openqa.selenium.WebElement;

import com.aaa.olb.automation.controls.Control;
import com.aaa.olb.automation.log.Log;

class ControlCollection<T extends Control> implements List<T> {

	private SeleniumContext context;

	private List<WebElement> elements;

	public ControlCollection(SeleniumContext context, List<WebElement> elements) {
		this.context = context;
		this.elements = elements;
	}

	@Override
	public boolean add(T e) {
		return this.elements.add((WebElement) e);
	}

	@Override
	public void add(int index, T element) {
		this.elements.add(index, (WebElement) element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		return this.elements.addAll((Collection<? extends WebElement>) c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return this.elements.addAll(index, (Collection<? extends WebElement>) c);
	}

	@Override
	public void clear() {
		elements.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.elements.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.elements.containsAll(c);
	}

	@SuppressWarnings("unchecked")
	private T generateItem(WebElement element) throws Exception {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> targetClass = (Class<T>) type.getActualTypeArguments()[0];
		Constructor<T> constructor = targetClass.getConstructor(SeleniumContext.class, WebElement.class);
		return constructor.newInstance(this.context, element);
	};

	@Override
	public T get(int index) {
		WebElement element = this.elements.get(index);
		try {
			return this.generateItem(element);
		} catch (Exception e) {
			Log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return this.elements.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return this.toList().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.elements.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return this.toList().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return this.toList().listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return this.elements.remove(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		return (T) this.elements.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.elements.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.elements.retainAll(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T element) {
		return (T) this.elements.set(index, (WebElement) element);
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	private List<T> toList() {
		List<T> results = new ArrayList<>();

		try {
			for (WebElement element : this.elements) {
				results.add(this.generateItem(element));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info(e.getLocalizedMessage());
		}

		return results;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return this.toList().subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return this.toList().toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return this.toList().toArray(a);
	}

}
