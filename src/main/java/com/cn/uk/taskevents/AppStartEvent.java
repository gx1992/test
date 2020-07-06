package com.cn.uk.taskevents;

import com.cn.uk.common.utils.ConcurrentMap;
import com.cn.uk.config.SmartGatewayConfig;
import com.cn.uk.socket.localRelate.SmartGatewayClient;
import com.cn.uk.taskevents.params.AppStartParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class AppStartEvent {

    private static final Logger logger = LoggerFactory.getLogger(AppStartEvent.class);
    @Autowired
    private SmartGatewayConfig smartGatewayConfig;

    @EventListener
    public void operate(AppStartParam appStartParam) {
        logger.debug("程序启动，开始启动相关线程");
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentMap.putItem("ExecutorService", executorService);

        if (smartGatewayConfig.isAllow()) {
            //网关socket监听成功
            SmartGatewayClient smartGatewayClient = new SmartGatewayClient(smartGatewayConfig.getIp(),
                    smartGatewayConfig.getPort());

            executorService.submit(smartGatewayClient);
            logger.info("启动网关socket监听成功");
            //存储连接
            ConcurrentMap.putItem("smartGatewayClient",smartGatewayClient);
        }

    }



}
