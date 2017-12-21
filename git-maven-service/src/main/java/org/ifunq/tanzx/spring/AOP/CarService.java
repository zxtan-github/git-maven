package org.ifunq.tanzx.spring.AOP;

/**
 * 汽车服务类接口
 * 
 * @author fruitking
 * @since 2010-02-23
 */
public interface CarService {

    /**
     * 启动汽车
     */
    public void start();

    /**
     * 获得汽车搭载人数
     * 
     * @return
     */
    public int getLoadAmount();

    /**
     * 设置驾驶员
     * 
     * @param driver
     * @return
     */
    public String setDriver(String driver);

    /**
     * 搭载货物
     * 
     * @param goods
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void loadGoods(String goods) throws NullPointerException, IllegalArgumentException;
}
