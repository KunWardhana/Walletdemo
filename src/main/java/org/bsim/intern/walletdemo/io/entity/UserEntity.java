package org.bsim.intern.walletdemo.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usertable")
@SequenceGenerator(name = "seqUser", initialValue = 100, allocationSize = 10)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -5310229491191927113L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    private long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletEntity> walletEntity = new ArrayList<>();

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

    public List<WalletEntity> getWalletEntity() {
        return walletEntity;
    }

    public void setWalletEntity(List<WalletEntity> walletEntity) {
        this.walletEntity = walletEntity;
    }
}
