package org.bsim.intern.walletdemo.io.repository;

import org.bsim.intern.walletdemo.io.entity.TransactionEntity;
import org.bsim.intern.walletdemo.io.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByWalletEntity(WalletEntity walletEntity);

    TransactionEntity findByTransactionID(String transactionid);
}
