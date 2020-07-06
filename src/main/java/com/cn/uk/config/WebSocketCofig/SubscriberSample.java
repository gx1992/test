package com.cn.uk.config.WebSocketCofig;

import com.google.common.io.BaseEncoding;
import okhttp3.WebSocket;

public class SubscriberSample {
    private String ip = "127.0.0.1"; //websocket服务器ip
    private String port = "8080"; //端口
    private String username = "management/cspg";//用户名
    private String passwd = "cspg!@#$";//密码
    String topic = "/events/115128";

    //"ws://" + ip + ":"+ port + "/cep/realtime/"
    //ws://121.40.165.18:8800
    public void wsTest() {
        Subscriber subscriber = Subscriber.create("ws://127.0.0.1:8080/cep/realtime", username + ":" + passwd);
        WebSocket client = subscriber.subscribe(topic, new Subscriber.MessageListener() {
            @Override
            public void onMessage(String dsn, String payload) {
                System.out.println("sn: " + dsn);
                String text = new String(BaseEncoding.base64().decode(payload));
                System.out.println("payload: " + text);
            }
        });
        client.send("ceshi1212231231");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignore) {
        }
    }

    public static void main(String[] args) {
        SubscriberSample ss = new SubscriberSample();
        ss.wsTest();
    }

}
