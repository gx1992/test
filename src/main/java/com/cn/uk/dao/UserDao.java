package com.cn.uk.dao;


import com.cn.uk.model.AccessToken;
import com.cn.uk.model.PlanItems;
import com.cn.uk.model.ShowImages;
import com.cn.uk.model.User;

import java.util.List;
import java.util.Map;
public interface UserDao {
    AccessToken getToken(String usr_sn);
    int createToken(AccessToken accessToken);
    int deleteToken(int usr_sn);

    List<User> getByRelTowerNo(Map<String, Object> params);


	User isLogin(Map<String, Object> map);
	int updateLoginTime(Map<String, Object> map);
	List<PlanItems> getMessageHistorys(Map<String, Object> map);
	int countHistorysData(Map<String, Object> map);

	User getByUsrCode(Map<String, Object> params);
	User getByAccessToken(Map<String, Object> map);
	List<ShowImages> getImagesUrl(Map<String, Object> map);
	Integer getTotal(Map<String, Object> map);
}
