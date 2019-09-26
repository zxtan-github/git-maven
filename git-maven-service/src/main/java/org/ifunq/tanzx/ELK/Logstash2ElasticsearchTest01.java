package org.ifunq.tanzx.ELK;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logstash2ElasticsearchTest01 {
    private static final Logger logger = LogManager.getLogger(Logstash2ElasticsearchTest01.class);
    public static void main(String[] args) {
        logger.info("[小米有品对接客户端] 调用方法:/openapi/shop/updatepackageexpress, 未加密请求参数:{bizcode=ems, expressSn=9748252238108, outPackageId=3201909191400120853S0001}");
        logger.info("小米有品对接客户端] 同步物流单号结果:{\"code\":400080050,\"message\":\"该包裹已同步, 无需重复同步\",\"result\":null}");
    }
}
