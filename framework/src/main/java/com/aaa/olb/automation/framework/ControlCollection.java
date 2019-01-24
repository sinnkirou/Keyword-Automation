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

class ControlCollection<T extends Control> implements List<T> {

	private SeleniumContext context;

	private List<WebElement> elements;

	public ControlCollection(SeleniumContext context, List<WebElement> elements) {
		this.context = context;
		this.elements = elements;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to add element");
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to add element");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to add elements");
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to add elements");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elements.clear();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to check element");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to check elements");
	}

	@SuppressWarnings("unchecked")
	private T generateItem(WebElement element) throws Exception {
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> targetClass = (Class<T>)type.getActualTypeArguments()[0];
		Constructor<T> constructor = targetClass.getConstructor(SeleniumContext.class, WebElement.class);
		return constructor.newInstance(this.context, element);
	};

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		WebElement element = this.elements.get(index);
		try {
			return this.generateItem(element);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to index of target");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.elements.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return this.toList().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to last index of target");
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return this.toList().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return this.toList().listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to remove");
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to remove");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to remove all target elements");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to retain all target elements");
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unable to set element");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.elements.size();
	}

	private List<T> toList() {
		List<T> results = new ArrayList<>();

		try {
			for (WebElement element : this.elements) {
				results.add(this.generateItem(element));
			}
		} catch (Exception e) {

		}
		
		return results;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return this.toList().subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.toList().toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return this.toList().toArray(a);
	}

}
