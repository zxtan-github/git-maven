package org.ifunq.tanzx.InnerOuterClass;
/**
 * 内部类
 * 访问外部类的属性和方法
 * @author tanzx
 */
class OuterWithInner {  
	public String publicString = "Outer.publicString";
    public void sayHello() {
    	System.out.println("Hello");
	}
    class Inner {  
        public void sayName() {
			System.out.println(publicString);
			sayHello();
		}
    }
}  
