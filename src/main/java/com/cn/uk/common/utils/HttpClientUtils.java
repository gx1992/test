package com.cn.uk.common.utils;


import com.alibaba.fastjson.JSON;
import com.cn.uk.controller.DoorEntranceController;
import com.cn.uk.dto.RtnData;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * author:wangxiao
 * 
 */
public class HttpClientUtils {


	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	// 编码格式。发送编码格式统一用UTF-8
	private static final String ENCODING = "UTF-8";
	// 设置连接超时时间，单位毫秒。
	private static final int CONNECT_TIMEOUT = 180000;
	// 请求获取数据的超时时间(即响应时间)，单位毫秒。
	private static final int SOCKET_TIMEOUT = 180000;
	//最大连接数
	private static final int MAX_CONNERCTION = 6000;
	//单个路由最大连接数
	private static final int MAX_PERROUTE = 1000;
	
	private final static PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager();  //连接池管理器
	private final static HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {  //retry handler
	        public boolean retryRequest(IOException exception,
	                                    int executionCount, HttpContext context) {
	            if (executionCount >= 5) {
	                return false;
	            }
	            if (exception instanceof NoHttpResponseException) {
	                return true;
	            }
	            if (exception instanceof InterruptedIOException) {
	                return false;
	            }
	            if (exception instanceof UnknownHostException) {
	                return false;
	            }
	            if (exception instanceof ConnectTimeoutException) {
	                return false;
	            }
	            HttpClientContext clientContext = HttpClientContext
	                    .adapt(context);
	            HttpRequest request = clientContext.getRequest();

	            if (!(request instanceof HttpEntityEnclosingRequest)) {
	                return true;
	            }
	            return false;
	        }
	    };
	    
	    static {   //类加载的时候 设置最大连接数 和 每个路由的最大连接数
	        poolConnManager.setMaxTotal(HttpClientUtils.MAX_CONNERCTION); 
	        poolConnManager.setDefaultMaxPerRoute(HttpClientUtils.MAX_PERROUTE);
	    }
	    
	    /**
	     * 从连接池中获取连接
	     * @return
	     */
	    private static CloseableHttpClient getCloseableHttpClient() {
	        CloseableHttpClient httpClient = HttpClients.custom()
	                .setConnectionManager(poolConnManager)
	                .setConnectionManagerShared(true) 
	                .setRetryHandler(httpRequestRetryHandler)
	                .build();

	        return httpClient;
	    }


	  /**
	   * 有请求头和参数的doGet请求
	   * @param url
	   * @param headers
	   * @param params
	   * @return
	   * @throws Exception
	   */
	public static RtnData doGet(String url, Map<String, String>headers, Map<String, String> params) throws Exception{
		CloseableHttpClient httpClient = getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(url);
		if(params !=null) {
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> entry : entrySet) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue());
			}
		}
		// 创建http对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		
		/**
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
		 * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse httpResponse=null;
		packageHeader(headers, httpGet);
		try {
			
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse,httpClient, httpGet);
				
		       } finally {
			 // 释放资源
				release(httpResponse, httpClient);
				
		      }
		
	}


	/**
	 * 有参数的doPost
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static RtnData doPost(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
		// 创建httpClient对象
		CloseableHttpClient httpClient = getCloseableHttpClient();

		// 创建http对象
		HttpPost httpPost = new HttpPost(url);
		/**
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
		 * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		// 设置请求头
        /*httpPost.setHeader("Cookie", "");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");*/
		packageHeader(headers, httpPost);
		// 封装请求参数
		packageParam(params, httpPost);

		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;

		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}

	
	



	/**
	 * 延时请求
	 * @param url
	 * @param jsonString
	 * @param duration
	 * @return
	 * @throws Exception
	 */
	public static RtnData doPost(String url,String jsonString,int duration) throws Exception {
		    Date lastSendTime = ConcurrentMap.getItem("dopost.url.sendtime:" + url, Date.class);
	        Date currentTime = new Date();

	        if(lastSendTime == null || currentTime.getTime() - lastSendTime.getTime() >= duration){
	            HttpClientUtils.doPost(url, jsonString);
	            ConcurrentMap.putItem("kafka.producer.sendtime:" + url, currentTime);
	        }
	        return new RtnData();
	}


	/**
	 * 请求体weijson字符串
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static RtnData doPost(String url, String jsonString) throws Exception {
		// 创建httpClient对象
		CloseableHttpClient httpClient = getCloseableHttpClient();

		// 创建http对象
		HttpPost httpPost = new HttpPost(url);
		/**
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
		 * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		StringEntity requestEntity = new StringEntity(jsonString,HttpClientUtils.ENCODING);
		requestEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(requestEntity);
		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;

		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}


	/**
	 * 请求体j'son字符串
	 * @param url
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static RtnData doPost(String url,Map<String,String> headParams, String jsonString) throws Exception {

		logger.info("开始执行doPost方法");
		// 创建httpClient对象
		CloseableHttpClient httpClient = getCloseableHttpClient();

		// 创建http对象
		HttpPost httpPost = new HttpPost(url);
		logger.info("创建http对象");
		/**
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
		 * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		packageHeader(headParams, httpPost);
		StringEntity requestEntity = new StringEntity(jsonString,HttpClientUtils.ENCODING);
		requestEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(requestEntity);
		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;

		try {
			// 执行请求并获得响应结果
			logger.info("开始执行getHttpClientResult, 执行请求并获得响应结果");
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}


	/**
	 * 请求体j'son字符串
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static RtnData doPost(String url,Map<String,String> headParams, Object json) throws Exception {

		logger.info("开始执行doPost方法");
		// 创建httpClient对象
		CloseableHttpClient httpClient = getCloseableHttpClient();

		// 创建http对象
		HttpPost httpPost = new HttpPost(url);
		logger.info("创建http对象");
		/**
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
		 * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		packageHeader(headParams, httpPost);
		String jsonString = JSON.toJSONString(json);
		StringEntity requestEntity = new StringEntity(jsonString,HttpClientUtils.ENCODING);
		requestEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(requestEntity);
		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;

		try {
			// 执行请求并获得响应结果
			logger.info("开始执行getHttpClientResult, 执行请求并获得响应结果");
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}


	/**
	 * post上传文件
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static RtnData postFile(String url, Map<String, ContentBody> param) throws Exception {

		CloseableHttpClient httpClient = getCloseableHttpClient();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(Charset.forName("utf-8"));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		for(Entry<String, ContentBody> entry : param.entrySet()) {
			builder.addPart(entry.getKey(),entry.getValue());
		}
		HttpEntity reqEntity = builder.build();
		httpPost.setEntity(reqEntity);
		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(response, httpClient, httpPost);
		} finally {
			// 释放资源
			release(response, httpClient);
		}
	}

	
	/**
	 * Description: 封装请求头
	 * @param params
	 * @param httpMethod
	 */
	public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
		// 封装请求头
		if (params != null) {
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> entry : entrySet) {
				// 设置到请求头到HttpRequestBase对象中
				httpMethod.setHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	
	/**
     * Description: 封装请求参数
     * 
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }
    
    
	/**
	 * Description: 获得响应结果
	 * 
	 * @param httpClient
	 * @param httpMethod
	 * @return
	 * @throws Exception
	 */
	public static RtnData getHttpClientResult(CloseableHttpResponse httpResponse,CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws Exception {
		// 执行请求
	    httpResponse = httpClient.execute(httpMethod);
		logger.info("执行请求开始...");
		RtnData rtnData = null;
		
		// 获取返回结果
		if (httpResponse != null && httpResponse.getStatusLine() != null) {
			String content = "";
			logger.info("httpResponse.getEntity()");
			if (httpResponse.getEntity() != null) {
				content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
			}

			logger.info("获取捷顺的数据信息:"+content);
			try{
				rtnData = JSON.parseObject(content,RtnData.class);
			}catch (Exception e){
				logger.error("捷顺数据转换json为RtnData对象失败!");
			}

		}
		return rtnData;
	}

	
	 /**
     * Description: 释放资源
     * 
     * @param httpResponse
     * @param httpClient
     * @throws IOException
     */
    public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        // 释放资源
        if (httpResponse != null) {
        	
        	EntityUtils.consume(httpResponse.getEntity());
            httpResponse.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
