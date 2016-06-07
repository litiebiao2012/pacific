package com.pacific.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Fe on 15/8/15.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginCheckAnnotation {
    boolean checked() default  true;
}
