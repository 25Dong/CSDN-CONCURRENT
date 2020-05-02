package com.mark.csdn.concurrent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 线程安全注解
 * @Author: Mark
 * @CreateDate: 2019/6/29 12:01
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";

}
