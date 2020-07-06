/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: commonStream
 * 创建者:   高旭
 * 生成日期:     2020/5/18 9:21
 * 描述: 数据流返回给主站通用方法
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.util;

import com.alibaba.fastjson.JSONObject;
import com.cn.uk.socket.localRelate.SmartGatewayHandler;

import java.io.OutputStream;

/**
 * 〈功能简述〉<br> 
 * 〈数据流返回给主站通用方法〉
 *
 * @author gaoxu
 * @create 2020/5/18 9:21
 * @since 1.0.0
 */
public class ConnectLocalStream {


    //通用方法
    public  static boolean commonStream(JSONObject json) throws  Exception{


        boolean flag = true;
        System.out.println("获取当前接口的json:"+json);
        OutputStream outputStream = SmartGatewayHandler.socket.getOutputStream();
        String jsonString =  json.toString();
        //将String转化为byte[]
        byte[] jsonByte = jsonString.getBytes();

        //向服务端发送数据
        if(outputStream !=null){
            System.out.println("向C++发送报文:"+json);
            System.out.println("发的数据长度为:"+jsonByte.length);
            outputStream.write(jsonByte);
            outputStream.flush();
            System.out.println("传输数据完毕");
            flag = true;
        }else {
            flag = false;
            System.out.println("与C++通信未连接,发送失败！");
        }
        return flag;
    }
}
