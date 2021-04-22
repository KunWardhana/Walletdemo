package org.bsim.intern.walletdemo.shared.dto;

import org.bsim.intern.walletdemo.io.entity.WalletEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {
    private static final long serialVersionUID= -338602314055514748L;

    private long id;
    private String name;
    private long amount;
    private LocalDateTime tanggal;
    private String note;
    private boolean isDeleted;
    private WalletDTO walletDTO;
    private String TransactionID;

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

    public WalletDTO getWalletDTO() {
        return walletDTO;
    }

    public void setWalletDTO(WalletDTO walletDTO) {
        this.walletDTO = walletDTO;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }
}
