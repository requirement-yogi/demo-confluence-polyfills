package org.apache.struts2.interceptor.parameter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Stub for Confluence 7.19, 7.20, 8.0 and up to 8.5.6 (excluded).
 * This class already exists (as provided) in Confluence 8.5.6 and 8.8.
 * Annotates setters used to inject URL values into *Action.java classes,
 * and annotates getters used in .vm templates.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrutsParameter {

    int depth() default 0;
}
