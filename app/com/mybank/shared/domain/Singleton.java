package com.mybank.shared.domain;

import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Singleton {
}
