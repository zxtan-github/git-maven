package org.ifunq.tanzx.tbbpm.process.ktv;

public class MockSpringBean {
	public void sing(String name) {
		System.out.println(name + " is singing");
	}
	public void payMoney(int price) {
		System.out.println("actually paid money: " + price);
	}
}
