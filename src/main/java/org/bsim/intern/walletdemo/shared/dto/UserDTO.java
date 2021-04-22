package org.bsim.intern.walletdemo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2975980106481240175L;
    private long id;
    private String userid;
    private String username;
    private List<WalletDTO> listwallet;

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

    public List<WalletDTO> getListwallet() {
        return listwallet;
    }

    public void setListwallet(List<WalletDTO> listwallet) {
        this.listwallet = listwallet;
    }
}
