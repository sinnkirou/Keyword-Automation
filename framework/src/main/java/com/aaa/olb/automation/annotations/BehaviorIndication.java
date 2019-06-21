package com.aaa.olb.automation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * name stands for the behavior name or action name, like click or enter..
 *
 * provider stands for the class name of specific behavior provider
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BehaviorIndication {

	String name();

	String provider();
}
