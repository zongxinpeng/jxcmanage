package com.jxc.jxcmanage.annotation;


import org.springframework.core.annotation.AliasFor;
import com.jxc.jxcmanage.enums.Role;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口拦截注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Api {

    @AliasFor("scope")
    Role[] value() default Role.PURCHASE;

    @AliasFor("value")
    Role[] scope() default Role.PURCHASE;

    /**
     * 是否在校验session中排除
     * @return
     */
    boolean exclude() default false;
}
