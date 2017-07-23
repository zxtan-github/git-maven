package org.ifunq.tanzx.InnerOuterClass;

/**
 * 匿名内部类
 * 访问外部类的属性和方法
 * @author tanzx
 */
public class OuterWithAnonymousInner {
	public String publicString = "Outer.publicString";
    public void sayHello() {
    	System.out.println("Hello");
	}
    // 继承接口
    People peopple = new People() {
		@Override
		public void sayWorld() {
			System.out.println(publicString);
			sayHello();
		}
	};
	// 继承类
	Object object = new Object() {
		public String toString() {
			System.out.println(publicString);
			sayHello();
			return null;
		}
	};
}
interface People {
	public void sayWorld();
}