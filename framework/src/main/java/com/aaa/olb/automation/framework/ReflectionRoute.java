package com.aaa.olb.automation.framework;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.aaa.olb.automation.annotations.ByClassName;
import com.aaa.olb.automation.annotations.ByCss;
import com.aaa.olb.automation.annotations.ById;
import com.aaa.olb.automation.annotations.ByLinkText;
import com.aaa.olb.automation.annotations.ByName;
import com.aaa.olb.automation.annotations.ByPartialLinkText;
import com.aaa.olb.automation.annotations.ByTag;
import com.aaa.olb.automation.annotations.ByXPath;

public class ReflectionRoute extends Route{
	
	protected Field field;
	

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	/*
	 *Class<?> getType() 返回一个 Class 对象，它标识了此 Field 对象所表示字段的声明类型。
     *Type getGenericType() 返回一个 Type 对象，它表示此 Field 对象所表示字段的声明类型。 
	 * */
	public static ReflectionRoute create(Field field) {
		boolean isControl = false;
		ReflectionRoute route = new ReflectionRoute();
		route.setField(field);
		route.setFieldType(field.getType());
		route.setFieldName(field.getName());
		
		/*
		 * handle the list control such as List<Input>
		 * */
		if(field.getType().isAssignableFrom(List.class)){
			ParameterizedType fieldType= (ParameterizedType) field.getGenericType();
			if(fieldType == null) {
				throw new UnsupportedOperationException("Cannot extract the target field type.");
			}
			route.setGeneric(true);
			route.setFieldType(fieldType.getActualTypeArguments()[0]);
		}
		
		ById byId = field.getAnnotation(ById.class);
		if (byId != null) {
			isControl = true;
			route.setLocationKind(LocationKind.ID);
			route.setLocation(byId.value());
		}

		ByCss byCss = field.getAnnotation(ByCss.class);
		if (byCss != null) {
			isControl = true;
			route.setLocationKind(LocationKind.CSS);
			route.setLocation(byCss.value());
		}

		ByXPath byXPath = field.getAnnotation(ByXPath.class);
		if (byXPath != null) {
			isControl = true;
			route.setLocationKind(LocationKind.XPATH);
			route.setLocation(byXPath.value());
		}

		ByClassName byClassName = field.getAnnotation(ByClassName.class);
		if (byClassName != null) {
			isControl = true;
			route.setLocationKind(LocationKind.CLASSNAME);
			route.setLocation(byClassName.value());
		}

		ByTag byTag = field.getAnnotation(ByTag.class);
		if (byTag != null) {
			isControl = true;
			route.setLocationKind(LocationKind.TAG);
			route.setLocation(byTag.value());
		}
		
		ByLinkText byLinkText = field.getAnnotation(ByLinkText.class);
		if (byLinkText != null) {
			isControl = true;
			route.setLocationKind(LocationKind.LINKTEXT);
			route.setLocation(byLinkText.value());
		}
		
		ByPartialLinkText byPLinkText = field.getAnnotation(ByPartialLinkText.class);
		if (byPLinkText != null) {
			isControl = true;
			route.setLocationKind(LocationKind.PARTIALLINKTEXT);
			route.setLocation(byPLinkText.value());
		}
		
		ByName byName = field.getAnnotation(ByName.class);
		if (byName != null) {
			isControl = true;
			route.setLocationKind(LocationKind.NAME);
			route.setLocation(byName.value());
		}

		if (isControl) {
			return route;
		}

		return null;
	}

}
