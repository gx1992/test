package com.cn.uk.model;


public class AccessToken {
    private String  usr_sn;
    private String token;

    

    public String getUsr_sn() {
		return usr_sn;
	}

	public void setUsr_sn(String usr_sn) {
		this.usr_sn = usr_sn;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "AccessToken{" +
                "usr_sn=" + usr_sn +
                ", token='" + token + '\'' +
                '}';
    }
}
