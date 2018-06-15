package com.singleton;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: ggg.java
 * Author:   raolesong
 * Date:     2016年2月17日 下午2:18:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommonConstants {
	/**
	 * @desc 订单类型
	 * @author tangkun
	 * 
	 */
	public enum ORDERTYPESTATE {
		HEADQUARTERSORDERS("总部订单 ", 0), DEALERORDERS("将销售", 1), DIRECTORDERS(
				"直销订单", 2);
		private final String name;
		private final Integer value;

		ORDERTYPESTATE(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Integer getValue() {
			return value;
		}
	}

	public static void main(String[] args) throws Exception {

		Class<?> class1 = Class
				.forName("CommonConstants$ORDERTYPESTATE");

		Field[] field = class1.getFields();
		for (Field field2 : field) {
			field2.setAccessible(true);
			Class<?> class2 = field.getClass();
			/**
			 * methods为空， 我想动态调用枚举中的变量的值
			 */
			Method[] methods = class2.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println(method.getName());
			}

		}
	}
}





