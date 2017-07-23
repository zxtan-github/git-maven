package org.ifunq.tanzx.SafePublish;

public class UnSafePublishTest {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		MyObeject myObeject = new MyObeject(myThread);
	}
}
class MyObeject {
	public boolean flag;
	public String name;
	public MyObeject(MyThread thread) {
		flag = true;
		thread.setMyObeject(this); // 显示逸出
		// doSomething... 中间有很长一段处理3s
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		name = "tanzongxi";
	}
}

class MyThread extends Thread {
	MyObeject myObeject;
	public MyObeject getMyObeject() {
		return myObeject;
	}
	public void setMyObeject(MyObeject myObeject) {
		this.myObeject = myObeject;
	}
	@Override
	public void run() {
		// 每隔0.1s就查询一次状态
		for (;;) {
			if (myObeject != null && myObeject.flag) {
				System.out.println("name : " + myObeject.name);
				break;
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {}
		}
	}
}