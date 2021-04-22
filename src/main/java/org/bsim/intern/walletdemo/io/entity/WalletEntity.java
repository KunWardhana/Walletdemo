package org.bsim.intern.walletdemo.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="wallettable")
@SequenceGenerator(name = "seqwallet", initialValue = 100, allocationSize = 10)
public class WalletEntity implements Serializable {
    private static final long serialVersionUID = -3790731346694351966L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqwallet")
    private long id;

    @Column(nullable = false)
    private String walletid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false)
    private String phonenumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity user;

    @OneToMany(mappedBy = "walletEntity")
    private List<TransactionEntity> transactionEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWalletid() {
        return walletid;
    }

    public void setWalletid(String walletid) {
        this.walletid = walletid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }
}
