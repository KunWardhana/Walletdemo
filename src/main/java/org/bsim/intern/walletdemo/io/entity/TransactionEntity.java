package org.bsim.intern.walletdemo.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID= -1546356866767244427L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private LocalDateTime tanggal;

    private String note;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private WalletEntity walletEntity;

    @Column(nullable = false)
    private String transactionID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public WalletEntity getWalletEntity() {
        return walletEntity;
    }

    public void setWalletEntity(WalletEntity walletEntity) {
        this.walletEntity = walletEntity;
    }
}
