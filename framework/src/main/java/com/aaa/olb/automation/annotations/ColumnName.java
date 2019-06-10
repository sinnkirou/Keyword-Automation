package com.aaa.olb.automation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * value should be consistent with template target name;
 * 
 * set shouldWait to true, will wait for specific time after operation;
 * 
 * set shouldDelay to true, will wait for specific time before operation;
 * 
 * set blur to true for input fields, will blur after entering;
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
	String value();
	boolean shouldWait() default false;
	boolean shouldDelay() default false;
	boolean blur() default false;
	boolean nested() default false;
}
