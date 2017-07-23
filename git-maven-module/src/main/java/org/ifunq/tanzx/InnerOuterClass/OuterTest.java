package org.ifunq.tanzx.InnerOuterClass;

import java.lang.reflect.Field;

import org.ifunq.tanzx.InnerOuterClass.OuterWithInner.Inner;

public class OuterTest {

	public static void main(String[] args) throws Exception, IllegalAccessException {
		
		// 内部类
		OuterWithInner outer1 = new OuterWithInner();
		Inner inner = outer1.new Inner();
		inner.sayName();
		
		// 匿名内部类
		OuterWithAnonymousInner outer2 = new OuterWithAnonymousInner();
		outer2.peopple.sayWorld();
		outer2.object.toString();
		
		System.out.println(inner.getClass()); // 查看类信息
		System.out.println(outer2.peopple.getClass()); // 查看类信息
		System.out.println(outer2.object.getClass()); // 查看类信息
		
		// 内部类
		Field[] fields1 = inner.getClass().getDeclaredFields();
		for (Field f : fields1) {
			System.out.println(f); 
		}
		// 匿名内部类
		Field[] fields2 = outer2.peopple.getClass().getDeclaredFields();
		for (Field f : fields2) {
			System.out.println(f); 
		} 
		
		OuterWithInner out1 = (OuterWithInner) inner.getClass().getDeclaredField("this$0").get(inner);
		out1.publicString = "XXX";
		OuterWithAnonymousInner out2 = (OuterWithAnonymousInner) 
				outer2.peopple.getClass().getDeclaredField("this$0").get(outer2.peopple);
		out2.publicString = "YYY";
		inner.sayName();
		outer2.peopple.sayWorld();
	}

}
