package com.cn.uk.service;


import com.cn.uk.dao.UserDao;
import com.cn.uk.model.AccessToken;
import com.cn.uk.model.PlanItems;
import com.cn.uk.model.ShowImages;
import com.cn.uk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getByUsrCode(Map<String, Object> params){
        return userDao.getByUsrCode(params);
    }


    public int createToken(AccessToken accessToken){
        //查询已有token
        AccessToken token = userDao.getToken(accessToken.getUsr_sn());

        if (token != null){
            //保持现有token值不变
            accessToken.setToken(token.getToken());
        }

        return userDao.createToken(accessToken);
    }

    public int deleteToken(int usrSn){
        return userDao.deleteToken(usrSn);
    }

	public User isLogin(Map<String, Object> map) {
		return userDao.isLogin(map);
	}

	public int updateLoginTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.updateLoginTime(map);
	}

	

	public int countHistorysData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.countHistorysData(map);
	}

	public List<PlanItems> getMessageHistorys(Map<String, Object> map) {
		
		return userDao.getMessageHistorys(map);
	}


	public User getByAccessToken(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.getByAccessToken(map);
	}


	public List<ShowImages> getImagesUrl(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.getImagesUrl(map);
	}


	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.getTotal(map);
	}

}
