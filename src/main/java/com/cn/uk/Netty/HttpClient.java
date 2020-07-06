/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HttpClient
 * 创建者:   高旭
 * 生成日期:     2020/6/17 14:51
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.Netty;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/17 14:51
 * @since 1.0.0
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

import java.net.URI;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_METHODS;

public class HttpClient {
    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientInboundHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            URI uri = new URI("/XMLReceiver.cgi");

            StringBuffer sb = new StringBuffer();
            sb.append("DT8018004400094&RTV20200620131520.XML&301&1592630120&PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0nVVRGLTgnID8 CjxyZXEgZGF0YT0nMzAxJyA " +
                    "Cgk8cHJqIHNuPSdEVDgwMTgwMDQ0MDAwOTQnIGNyZWF0ZWRUbT0nMTU5MjYzMDExMycgZGVhZGxpbmU9JzIwMjAtMDYtMjAnIGRhaWx5U2VxPSc5NTExJyB0b3RhbFNlcT0nMTQ0MTInID4KCQk8ZGV2IGlkPScxJyBjYXRlZ29yeT0nMCcgPgoJCTwvZGV2PgoJCTxkZXYgaWQ9JzExMTEnIGNhdGVnb3J5PScxJyA CgkJCTxpdGVtIHNpZ0lkPScxMjEyJyB2YWw9JzIyMDAuMDAwMDAwMCcgdG09JzE1OTI2MzAxMTEnIC8 CgkJCTxpdGVtIHNpZ0lkPScxNDA0JyB2YWw9JzIyMDAuMDAwMDAwMCcgdG09JzE1OTI2MzAxMTEnIC8 CgkJPC9kZXY CgkJPGRldiBpZD0nNjAwMDEnIGNhdGVnb3J5PScwJyA CgkJPC9kZXY CgkJPGRldiBpZD0nNjAwMDInIGNhdGVnb3J5PScwJyA CgkJPC9kZXY CgkJPGRldiBpZD0nNjAwMTEnIGNhdGVnb3J5PScwJyA CgkJPC9kZXY CgkJPGRldiBpZD0nNjAwMTMnIGNhdGVnb3J5PScwJyA CgkJPC9kZXY Cgk8L3Byaj4KPC9yZXE Cg");

            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(sb.toString().getBytes("UTF-8")));

            // 构建http请求
            //门禁
            /*request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());

            */

            //动环
            request.headers().set(CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
            request.headers().set(CONTENT_LENGTH, request.content().readableBytes());

            request.headers().set(ACCEPT,"text/plain,*/*");
            request.headers().set(ACCEPT_ENCODING,"gzip,deflate,sdch");
            request.headers().set(ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8");
            //允许跨域访问
            request.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
            request.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"Origin, X-Requested-With, Content-Type, Accept");
            request.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"GET, POST, PUT,DELETE");




            // 发送http请求
            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.connect("127.0.0.1", 8905);
    }
}