/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ApiMapping.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午2:05:04
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午2:05:04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ApiMapping {

	String value() default "";
}
