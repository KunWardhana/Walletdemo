package org.bsim.intern.walletdemo.io.repository;

import org.bsim.intern.walletdemo.io.entity.UserEntity;
import org.bsim.intern.walletdemo.io.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    List<WalletEntity> findAllByUser(UserEntity userEntity);
    WalletEntity findByWalletid(String walletid);
}
