package com.mark.csdn.concurrent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 不推荐使用的注解
 * @Author: Mark
 * @CreateDate: 2019/6/29 12:04
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";

}
