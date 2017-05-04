package org.ifunq.tanzx.tbbpm.process.order;

public class OrderProcessHander {
	
	public boolean xiaDan(String arg) {
		if ("xiaDan".equals(arg)) {
			System.out.println("下单成功");
			return true;
		} else {
			System.out.println("下单失败");
			return false;
		}
	}
	
	public boolean chuKu(String arg) {
		if ("chuKu".equals(arg)) {
			System.out.println("出库成功");
			return true;
		} else {
			System.out.println("出库失败");
			return false;
		}
	}
	
	public boolean peiSong(String arg) {
		if ("peiSong".equals(arg)) {
			System.out.println("配送成功");
			return true;
		} else {
			System.out.println("配送失败");
			return false;
		}
	}
	
	public boolean error() {
		System.out.println("发生异常");
		return false;
	}

}
