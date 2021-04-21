package org.bsim.intern.walletdemo.shared.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2975980106481240175L;
    private long id;
    private String userid;
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
