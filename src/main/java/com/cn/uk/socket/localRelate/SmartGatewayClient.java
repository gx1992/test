package com.cn.uk.socket.localRelate;


import com.cn.uk.config.AppContext;
import com.cn.uk.config.SmartGatewayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * 与智能网关通信的socket客户端
 * @author wx
 */
public class SmartGatewayClient implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(SmartGatewayClient.class);
    private SmartGatewayConfig smartGatewayConfig = AppContext.getBean(SmartGatewayConfig.class);
    public volatile boolean stopFlag = false;
    public static Socket smartGatewayScoket = null;
    public SmartGatewayHandler smartGatewayHandler = null;

    private String ip;
    private int port;

    public SmartGatewayClient (String ip,int port){
             this.ip = ip;
             this.port = port;
    }

    @Override
    public void run() {
        while(!stopFlag){
            if(smartGatewayScoket == null){
                try {
                    smartGatewayScoket = new Socket(ip,port);
                    this.smartGatewayScoket.setKeepAlive(true);
                    this.smartGatewayHandler = new SmartGatewayHandler(this.smartGatewayScoket);

                    //加入线程池
                    Executors.newCachedThreadPool().submit(smartGatewayHandler);

                } catch (IOException e) {
                    logger.info("与智能网关socket连接失败");
                    e.printStackTrace();
                }
            }else {
                try {
                    this.smartGatewayScoket.sendUrgentData(0xFF);
                } catch (IOException e) {
                    logger.error("发送智能网关socket心跳包失败",e);
                    try {
                        if (this.smartGatewayHandler != null) {
                            this.smartGatewayHandler.setStopFlag(true);

                            logger.debug("发送心跳包失败，停止智能网关报文处理程序");
                        }

                        this.smartGatewayScoket.shutdownInput();
                        this.smartGatewayScoket.shutdownOutput();
                        this.smartGatewayScoket.close();
                        this.smartGatewayScoket = null;

                        logger.debug("发送心跳包失败，关闭于智能网关的socket连接");
                    } catch (IOException e1) {
                        this.smartGatewayScoket = null;
                        logger.error("smartGateway socket err", e1);
                    }
                }
            }
            //等待10秒
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("");
            }



        }

    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    public Socket getSmartGatewayScoket() {
        return smartGatewayScoket;
    }

    public InputStream getInputStream() {
        if(this.smartGatewayScoket != null ){
            try {
                return this.smartGatewayScoket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("获取智能网关的输入刘失败");
            }
        }

        return null;

    }
    public OutputStream getOutputStream() {
        if(this.smartGatewayScoket != null){
            try {
                return this.smartGatewayScoket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("获取智能网关的输出流失败");
            }
        }
        return null;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
