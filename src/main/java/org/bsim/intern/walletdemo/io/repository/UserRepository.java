package org.bsim.intern.walletdemo.io.repository;

import org.bsim.intern.walletdemo.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUserid(String userid);
}
