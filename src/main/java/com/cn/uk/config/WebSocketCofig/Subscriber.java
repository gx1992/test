package  com.cn.uk.config.WebSocketCofig;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class Subscriber
{
    private OkHttpClient client;
    private final String url;
    private final String auth;

    private Subscriber(String url, String auth) {
        this.client = (new OkHttpClient.Builder()).connectTimeout(60L, TimeUnit.SECONDS).build();
        this.url = url;
        this.auth = auth;
    }

    public static Subscriber create(String url, String auth) { return new Subscriber(url, auth); }


    public WebSocket subscribe(final String topic, final MessageListener messageListener) {
        return getWebsocket(new WebSocketListener()
        {
            String id;
            String clientId;

            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                webSocket.send(Subscriber.this.getHandshake());
            }


            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                webSocket.close(1000, null);
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                webSocket = Subscriber.this.getWebsocket(this);
            }


            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                try {
                    JSONArray base = new JSONArray(text);
                    JSONObject data = base.getJSONObject(0);
                    if (data.has("channel")) {
                        String channel = data.getString("channel");
                        /*if ("/meta/handshake".equals(channel)) {
                            this.id = data.getString("id");
                            this.clientId = data.getString("clientId");
                            webSocket.send(Subscriber.this.getConnect(this.clientId));
                            webSocket.send(Subscriber.this.getSubscribe(this.clientId, this.id, topic));
                        }*/
                        if (data.has("data")) {
                            String dsn = data.getJSONObject("data").getJSONObject("data").getJSONObject("source").getString("name");
                            String payload = data.getJSONObject("data").getJSONObject("data").getString("text");
                            messageListener.onMessage(dsn, payload);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void disconnect(WebSocket client, String clientId, String id) {
        if (client != null) {
            client.send(getDisconnect(clientId, id));
        }
    }

    private WebSocket getWebsocket(WebSocketListener listener) {
        Request request = (new Request.Builder()).url(this.url).build();
        return this.client.newWebSocket(request, listener);
    }

    private String getHandshake() {
        JSONObject handshake = new JSONObject(this);

        return handshake.toString();
    }

    private String getConnect(String clientId) {
        JSONObject connect = new JSONObject(clientId);
        return connect.toString();
    }

    private String getSubscribe(String clientId, String id, String topic) {
        JSONObject subscribe = new JSONObject(this, new String[]{id, clientId, topic});
        return subscribe.toString();
    }

    private String getDisconnect(String clientId, String id) {
        JSONObject subscribe = new JSONObject(this,new String[] {clientId,id});
        return subscribe.toString();
    }

    public static interface MessageListener {
        void onMessage(String param1String1, String param1String2);
    }
}