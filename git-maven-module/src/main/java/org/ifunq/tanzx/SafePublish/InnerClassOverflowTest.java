package org.ifunq.tanzx.SafePublish;

public class InnerClassOverflowTest {

	public static void main(String[] args) {
		MyThread2 myThread2 = new MyThread2();
		myThread2.start();
		MyObeject2 myObeject2 = new MyObeject2(myThread2);
	}

}

class MyObeject2 {
	private String name;
	class MyObejectInner {
		public void sayName () {
			System.out.println(name);
		}
	}
	public MyObeject2(MyThread2 thread) {
		thread.myObejectInner = new MyObejectInner(); // 隐式逸出
		// doSomething... 中间有很长一段处理3s
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		name = "tanzongxi";
	}
}

class MyThread2 extends Thread {
	
	public MyObeject2.MyObejectInner myObejectInner;

	@Override
	public void run() {
		// 每隔0.1s就查询一次状态
		for (;;) {
			if (myObejectInner != null) {
				myObejectInner.sayName();
				break;
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {}
		}
	}
}
