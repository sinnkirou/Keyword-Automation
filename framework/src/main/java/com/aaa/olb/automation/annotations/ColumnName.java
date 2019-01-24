package com.aaa.olb.automation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * value should be consistant with template target name;
 * 
 * set aysnc to true, will wait for specific time;
 * 
 * set blur to true for input fileds, will blur after entering;
 * 
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
	String value();
	boolean async() default false;
	boolean blur() default false;
}
