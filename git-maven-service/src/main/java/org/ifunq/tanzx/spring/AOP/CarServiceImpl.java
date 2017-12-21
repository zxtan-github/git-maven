package org.ifunq.tanzx.spring.AOP;

/**
 * 汽车服务类接口实现
 * 
 * @author fruitking
 * @since 2010-02-23
 */
public class CarServiceImpl implements CarService {

    /**
     * 启动汽车
     */
    public void start() {
        System.out.println("start my car...");
    }

    /**
     * 获得汽车搭载人数
     * 
     * @return
     */
    public int getLoadAmount() {
        System.out.println("count the person amount in my car...");
        return 5;
    }

    /**
     * 设置驾驶员
     * 
     * @param driver
     * @return
     */
    public String setDriver(String driver) {
        System.out.println("driver is:" + driver);
        if (driver == null || "".equals(driver)) {
            return "There is not driver.";
        } else {
            return "The driver's name is " + driver + ".";
        }
    }

    /**
     * 搭载货物
     * 
     * @param goods
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void loadGoods(String goods) throws NullPointerException, IllegalArgumentException {
        if (goods == null || "".equals(goods)) {
            throw new NullPointerException("The argument goods is null.");
        } else if ("tiger".equals(goods)) {
            throw new IllegalArgumentException("The argument goods is invalid.");
        }
        System.out.println("load goods is:" + goods);
    }
}
