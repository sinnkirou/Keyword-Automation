package com.aaa.olb.automation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * value should be consistent with template target name;
 * 
 * set async to true, will wait for specific time;
 * 
 * set blur to true for input fields, will blur after entering;
 * 
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
	String value();
	boolean async() default false;
	boolean blur() default false;
}
