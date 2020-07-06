package com.cn.uk.controller;

import com.cn.uk.common.pagination.PageHelper;
import com.cn.uk.common.utils.UUIDUtil;
import com.cn.uk.config.Layout;
import com.cn.uk.config.WebConfigurer;
import com.cn.uk.dto.LoginParam;
import com.cn.uk.dto.RtnData;
import com.cn.uk.model.HistorysParam;
import com.cn.uk.model.Page;
import com.cn.uk.model.PaginationUtils;
import com.cn.uk.model.PlanItems;
import com.cn.uk.model.ShowImages;
import com.cn.uk.model.User;
import com.cn.uk.model.XmlRequestTime;
import com.cn.uk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Layout(value = "layouts/default")
public class HomeController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final UserService userService;
    
    @Value("${linux.xml.urlHear}")
    private String urlHear;
    @Value("${linux.xml.ipServer}")
    private String ipServer;
    
    private Date chaoQi;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login.html")
    @Layout(value = "none")
    public String login() {
        return "login";
    }
    
    
    
    //视频数据认证接口
    @PostMapping("/butler/sso/login")
    @ResponseBody
    public RtnData butler(@RequestBody LoginParam loginParam,HttpSession session) {
    	
    	
    	Map<String, Object> params = new HashMap<>();
        params.put("musr_code", loginParam.getAccount());

        User userInfo = userService.isLogin(params);

        RtnData data = new RtnData();
        
        if(userInfo == null || userInfo.getMusr_code() == "") {
        	data.setCode(114);
            data.setMessage("该用户不存在");
            data.setSuccess(false);

            return data;
        }

        if(!userInfo.getMusr_pwd().equals(loginParam.getPassword())){
        	data.setCode(112);
            data.setMessage("账号/密码错误");
            data.setSuccess(false);

            return data;
        }

        //记录到表
        Date date = new Date();  
        
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Map<String,Object> map = new HashMap<String,Object>();
        
        map.put("musr_code", userInfo.getMusr_code());
        
        String generateUUID = "";

        //合理时长
        int outTime = userInfo.getToken_valid_minutes();
        //上一次登录时间
        Date login_time22 = userInfo.getLogin_time();
        SimpleDateFormat sb1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //上一次登录时间格式化
	    String formatt = sb1.format(login_time22);
	    
        Date login_time = userInfo.getLogin_time();
   	
        	Date a = new Date();   
        	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
        	if(userInfo.getToken() !=null) {
        		
        		Calendar c = Calendar.getInstance();
                c.setTime(login_time);
            	login_time.setTime(login_time.getTime() + 30*60*1000); //30分钟
                //上一次过期时间格式化
            	String format2 = sf.format(login_time.getTime());
             	try {
             	    //上一次过期时间
             		chaoQi = format4.parse(format2); 
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}	
        		//格式转换
        		Calendar g = Calendar.getInstance();
        		g.setTime(a);
        		String format3 = sf.format(g.getTime());
        		Date parse1 =null;
				try {
					parse1 = format4.parse(format3);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}  
        		
        		
        		int compareTo = parse1.compareTo(chaoQi);
        		if (compareTo == 1) { 
        			//login_time22.setTime(login_time22.getTime() + 30*60*1000); //30分钟
                 	map.put("login_time", format3); //新登录时间
        			generateUUID = UUIDUtil.generateUUID();
        		}else { //当前时间小于设定时间
        			map.put("login_time", formatt); //旧
        			generateUUID = userInfo.getToken();
        		}
        	}else {
    			map.put("login_time", formatt);//旧
        		generateUUID = UUIDUtil.generateUUID();
        	}
        map.put("token", generateUUID);
        map.put("token_valid_minutes", outTime);//有效时长(默认一天)
        userService.updateLoginTime(map);
        

        //设置session
        session.setAttribute(WebConfigurer.SESSION_KEY, userInfo);

      //成功返回token
        map.clear();
        map.put("token", generateUUID);
        data.setData(map); 
        data.setSuccess(true);
        data.setMessage("登录成功");
        
        return data;
    }
    

    
    
    //视频数据事件查询
    @PostMapping("/message/messageSever/getMessageHistorys")
    @ResponseBody
    public RtnData getMessageHistorys(@RequestBody HistorysParam hParam) {
    	
    	RtnData rtnData = new RtnData();
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> data = new HashMap<String,Object>();
    	
    	List<Object> list = new ArrayList<Object>(); 
    	
    	map.put("msgType", hParam.getMsgType());
    	map.put("startTime", hParam.getStartTime());
    	map.put("endTime", hParam.getEndTime());
    	
    	// 查询总条数
        int totalCount = userService.countHistorysData(map);
    	
    	 // 构建分页
        Page pagination = PaginationUtils.getPageParam(totalCount, hParam.getPageSize(), hParam.getPageNum());

        map.put("startIndex", pagination.getStartIndex());
        map.put("endIndex", pagination.getEndIndex());

    	//数据库查询
    	
    	List<PlanItems> planItems =  userService.getMessageHistorys(map);
    	
    	if(planItems != null) {
    		for(int i = 0;i<planItems.size();i++) {
    			
    			Map<String,Object> items = new HashMap<String,Object>();
    			PlanItems its = planItems.get(i);
    			
    			String alarm_name = its.getAlarm_name();
    			String run_time = its.getRun_time();
    			run_time = run_time.split("\\.")[0];
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    			String timeStap = "";
    			try {
    				long timeStampSec = sdf.parse(run_time).getTime()/1000;
    		        timeStap = String.format("%010d", timeStampSec);
    				
    			} catch (ParseException e) {
    				e.printStackTrace();
    			} 
    			items.put("msgType", hParam.getMsgType());
    			items.put("msgTypeCn", alarm_name); //alarm_fine
    			
    			
    			
    			items.put("msgId", its.getPlan_no()+"_"+its.getItem_no()+"_"+timeStap); //前三个  plan_no/run_time/item_no
    			items.put("videoUrl", its.getVideo_url()); //video_url
    			items.put("areaName", "220kv"); //xiesi
    			items.put("imageUrl", its.getPic_url()); //pic_url
    			items.put("createDate", timeStap); //run_time
    			items.put("imageTime", timeStap); //run_time
    			items.put("channelAlias", "208"); //plan表关联的摄像头
    			items.put("areaId","245");//写死
    			items.put("cameraId","195");//写死
    			
    			String alarm_pic_urls = its.getAlarm_pic_urls();
    			
    			String [] imageUrls = {alarm_pic_urls}; //alarm_pic_urls
    			
    			items.put("imageUrls", imageUrls);
    			list.add(items);
    		}
    	}
    	
    	data.put("items", list);  //item可以有多个
    	data.put("pageNum", hParam.getPageNum());
    	data.put("pageSize", hParam.getPageSize());
    	data.put("totalCount", totalCount);
    	data.put("systemTime", System.currentTimeMillis()); //run_time
    	data.put("serverFileAddress", "");  
    	//总条数/一页条数
    	data.put("totalPage", pagination.getTotalSize());
    	
    	map.clear();
    	
    	return rtnData.ok(data);
    }
    
    @GetMapping(value = "/showAlarmPic")
    public String  showAlarmPic() {
    	
    	
    	return "showImagesMsg.html";
    	
    }
    
    @RequestMapping(value = "/show")
    @ResponseBody
    public PageHelper<ShowImages>  showAlarmPic2(@RequestParam(required = false, value = "pageSize", defaultValue = "10") long pageSize,
												 @RequestParam(required = false, value = "pageIndex", defaultValue = "1") long pageIndex,
												 String type) {
    	
    	System.out.println(type);
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	
    	map.put("type", type);
    	

    	PageHelper<ShowImages> pageHelper = new PageHelper<ShowImages>();
		// 统计总记录数
		Integer total = userService.getTotal(map);
		pageHelper.setTotal(total);

		// 查询当前页实体对象
		map.put("pageSize", pageSize);
    	map.put("pageIndex", pageIndex);
		List<ShowImages> obj = userService.getImagesUrl(map);

		List<ShowImages> list=new ArrayList<ShowImages>();
		if(obj.size()>0) {
			
			for(int i=0;i<obj.size();i++){
				ShowImages v=new ShowImages();
				v.setId(i);
				v.setAlarm_name(obj.get(i).getAlarm_name());
				SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date = sb.format(obj.get(i).getRun_time());
				v.setN_date(date);
				v.setPic_url(obj.get(i).getPic_url());
				v.setRemark(obj.get(i).getRemark());
				list.add(v);
			}
		}
		
		pageHelper.setRows(list);
	 
		return pageHelper;
    	
    	
    	
    	
    	
    	
    	
    	//return rtn.ok(obj);
    	
    }
    
    
    @RequestMapping(value = "/showXML")
    @ResponseBody
    public RtnData  showXML(@RequestBody XmlRequestTime xTime) {
    	
    	RtnData rtn = new RtnData();
    	
    	String url = urlHear;
    	String ip = ipServer;
    	
    	File file=new File(url); //File.separator表示根目录，比如现在就表示在D盘下。
    	File[] tempList = file.listFiles();
    	
    	List<String> xmlUrl =  new ArrayList<String>();
    	if(tempList.length>0) {
    		
    		for (int i = 0; i < tempList.length; i++) {
    			if (tempList[i].isFile()) {
    				
    				String originFile = tempList[i].getName();
    				String fileTime = tempList[i].getName().replace("_","").replace(".", "").replaceAll("[a-zA-Z]","");
    				
    				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    				LocalDateTime ldt = LocalDateTime.parse(fileTime,dtf);
    				DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    				String datetime = ldt.format(fa);
    				
    				//时间字符串比较
    				String startTime = xTime.getStartTime();
    				String endTime = xTime.getEndTime();
    				
    				
    				if((datetime.compareTo(startTime)>0) &&  (datetime.compareTo(endTime)<0)) { //判断时间是否在之间
    					
    					xmlUrl.add(ip+"/getXml?url="+originFile);
    					
    				}
    			}
    		}
    		
    		//判断list是否有值，如果没有提示
    		if(xmlUrl.size() == 0)
    			xmlUrl.add("该时间范围内,暂无XML数据");
    		
    	}else {
    		xmlUrl.add("该时间范围内,暂无XML数据");
    	}
    	
    	return rtn.ok(xmlUrl);
    }
    
    
    @GetMapping("/getXml")
    public void getXml(String url, HttpServletResponse response) {
    	
    	String urlHead = urlHear;
    	File file = new File(urlHead+File.separator+ url);
    	
    	try {

            response.setContentType("application/xml");

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                OutputStream stream = response.getOutputStream();

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    stream.write(buffer, 0, len);
                }

                fis.close();
                stream.flush();
                stream.close();
            } else { //无数据
               // response.sendRedirect("/images/0_2.jpg");
            }
        } catch (Exception e) {
            logger.error("获取本地图片文件失败", e);
        }
    }
    
    
    
/*    @PostMapping(value = "/login.do")
    @ResponseBody
    public RtnData doLogin(@RequestParam(required = true, value = "userName") String userName,
                           @RequestParam(required = true, value = "password") String password,
                           HttpServletRequest request,
                           HttpSession session) {

        Map<String, Object> params = new HashMap<>();
        params.put("usr_code", userName);

        User userInfo = userService.getByUsrCode(params);

        RtnData data = RtnData.fail(null, "");
        if(userInfo == null || userInfo.getUsr_sn() == 0) {
            data.setMessage("该用户不存在");

            return data;
        }

//        if(!userInfo.getPassword().equals(SecurityUtil.MD5(password))) {
//            return -2;
//        }
        if(!userInfo.getPwd().equals(password)){
            data.setMessage("密码错误");

            return data;
        }

        //生成accesstoken
        AccessToken accessToken = new AccessToken();
        accessToken.setUsr_sn(userInfo.getUsr_sn());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 6);
        accessToken.setExpire(calendar.getTime());

        accessToken.setUserhost(StringUtil.getIpAddress(request));
        accessToken.setToken(UUIDUtil.generateUUID());

        userService.createToken(accessToken);

        userInfo.setAccessToken(accessToken.getToken());

        //设置session
        session.setAttribute(WebConfigurer.SESSION_KEY, userInfo);

        return RtnData.ok(userInfo);
    }

    @PostMapping("/autoLogin")
    @ResponseBody
    public RtnData doAutoLogin(String accessToken, HttpSession session){
        User userInfo = userService.getByAccessToken(accessToken);

        if (userInfo != null && userInfo.getExpire().getTime() > new Date().getTime()){
            //设置session
            session.setAttribute(WebConfigurer.SESSION_KEY, userInfo);

            return RtnData.ok(userInfo);
        }

        return RtnData.fail(null, "授权已过期，请重新登录");
    }

    @PostMapping("/loginOut.do")
    @ResponseBody
    public RtnData doLoginOut(HttpSession session) {
        //清除登录session
        session.removeAttribute(WebConfigurer.SESSION_KEY);

        return RtnData.ok("成功退出登录");
    }*/
}
