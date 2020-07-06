package com.cn.uk;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.command.HisItem;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.service.QueryTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */

public class AppTest
{
    /**
     * Rigorous Test :-)
     */


    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {

        byte [] packet = new byte[10];
        packet[0]=-21;
        packet[1]=-112;

        packet[8] = -21;
        packet[9] = -112;

        System.out.println(packet.length);
        byte[] bytes = subBytes(packet, 0, 2);
        byte[] bytes1 = subBytes(packet, packet.length-2, 2);
        System.out.println(bytes1);
        if(bytes[0] ==-21 && bytes[1]==-112 && bytes1[0] == -21 && bytes1[1] == -112 ){
            System.out.println(true);
        }
        //判断packet字节数组前两位是否为  0xEB90 {-112, -21}
        String content = new String(packet,0,2); //从位置0开始获取2个字节

        System.out.println(bytes);


    }



    /**
 * 从一个byte[]数组中截取一部分
 * @param src
 * @param begin
 * @param count
 * @return
 */
public static byte[] subBytes(byte[] src, int begin, int count) {
    byte[] bs = new byte[count];
    for (int i=begin;i<begin+count; i++) bs[i-begin] = src[i];
    return bs;
}

}
