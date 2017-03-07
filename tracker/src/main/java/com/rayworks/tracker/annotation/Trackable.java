package com.rayworks.tracker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sean on 3/6/17.
 * <p>
 * Indicates that the annotated method is being traced.
 *
 * @see TargetScope
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Trackable {
    TargetScope value();
}
