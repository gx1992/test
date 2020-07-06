package com.cn.uk.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.cn.uk.common.utils.StringUtil;
import com.cn.uk.dto.RtnData;
import com.cn.uk.model.User;
import com.cn.uk.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("validating access token");

        boolean isValidated = false;
        RtnData rtnData = new RtnData();

        String authorization = request.getHeader("Authorization");

        if (StringUtil.isEmpty(authorization)) {
            isValidated = false;
            rtnData.setCode(100);
            rtnData.setMessage("token 为空");
            rtnData.setSuccess(false);
        } else {
            String accessToken = authorization.replace("Bear ", "");

            if (StringUtil.isEmpty(accessToken)) {
                isValidated = false;
                rtnData.setCode(100);
                rtnData.setMessage("token 为空");
                rtnData.setSuccess(false);
            } else {
            	Map<String,Object> map = new HashMap<String,Object>();
            	map.put("token", accessToken);
               User userInfo = userService.getByAccessToken(map);

              if (userInfo == null) {
                    isValidated = false;
                    rtnData.setCode(113);
                    rtnData.setMessage("token 已失效，请重新登录");
                    rtnData.setSuccess(false);
                } else {
                	//TODO 判断token是否过期
                	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                	
                	Date a = new Date();   
                	Date b = userInfo.getLogin_time(); //该用户token在表中存储的时间
                	
                	//格式转换
            		String f3 = sf.format(a.getTime());
            		String f4 = sf.format(b.getTime());
            		Date parse1 =null,parse2 =null;
    				try {
    					parse1 = format4.parse(f3);
    					parse2 = format4.parse(f4);
    				} catch (ParseException e1) {
    					e1.printStackTrace();
    				} 
                	
    				int compareTo = parse1.compareTo(parse2);
            		if (compareTo == 1) { 
                        rtnData.setCode(113);
                        rtnData.setMessage("token 已失效，请重新登录");
                        rtnData.setSuccess(false);
                    }
                	
                    HttpSession session = request.getSession();
                    session.setAttribute(WebConfigurer.SESSION_KEY, userInfo);

                    isValidated = true;
                }
            }
        }

        if (!isValidated){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(rtnData.toString());
        }

        return isValidated;
    }
}
