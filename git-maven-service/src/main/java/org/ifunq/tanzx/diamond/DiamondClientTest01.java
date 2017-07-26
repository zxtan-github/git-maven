package org.ifunq.tanzx.diamond;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

import java.util.concurrent.Executor;

/**
 * Created by tanzongxi on 2017/7/26.
 */
public class DiamondClientTest01 {

    public static void main(String[] args) throws Exception {
        //填写你服务端后台保存过的group和dataId
        DiamondManager manager = new DefaultDiamondManager("IFUNQ", "org.tanzx.diamond.user.add", new ManagerListener() {
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("changed config: " + configInfo);
                    }

                    public Executor getExecutor() {
                        return null;
                    }
                });
        //设置diamond-server服务的端口
        manager.getDiamondConfigure().setPort(8080);

        String availableConfigureInfomation = manager.getAvailableConfigureInfomation(5000);
        System.out.println("start config: " + availableConfigureInfomation);

    }
}
