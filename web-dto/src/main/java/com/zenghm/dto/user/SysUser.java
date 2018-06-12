package com.zenghm.dto.user;

import java.io.Serializable;

/**
 * Create date:2018/06/12.
 * Created by: Airlen.
 * Class name:SysUser.
 */
public class SysUser implements Serializable{
    private static final long serialVersionUID = -6017955099373369255L;
    private String userid;
    private String username;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
