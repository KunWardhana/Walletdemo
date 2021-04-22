package org.bsim.intern.walletdemo.service.impl;

import org.bsim.intern.walletdemo.io.entity.UserEntity;
import org.bsim.intern.walletdemo.io.entity.WalletEntity;
import org.bsim.intern.walletdemo.io.repository.UserRepository;
import org.bsim.intern.walletdemo.io.repository.WalletRepository;
import org.bsim.intern.walletdemo.service.iservice.IWalletService;
import org.bsim.intern.walletdemo.shared.dto.UserDTO;
import org.bsim.intern.walletdemo.shared.dto.WalletDTO;
import org.bsim.intern.walletdemo.shared.util.GenerateRandomPublicID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements IWalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenerateRandomPublicID generateRandomPublicID;

    @Override
    public WalletDTO addNewWallet(String userid, WalletDTO walletDTO) {
        ModelMapper mapper = new ModelMapper();

        walletDTO.setWalletid(generateRandomPublicID.generateUserId( 30));
        // get user
        UserEntity userdata = userRepository.findByUserid(userid);
        // set user
        walletDTO.setUser(mapper.map(userdata, UserDTO.class));
        //Wallet entity
        WalletEntity entity = mapper.map(walletDTO, WalletEntity.class);
        //save data ke database
        WalletEntity storeData = walletRepository.save(entity);



        return mapper.map(storeData, WalletDTO.class);
    }

    @Override
    public List<WalletDTO> getAllWalletData(String userid) {
        List<WalletEntity> walletdata = walletRepository.findAllByUser(userRepository.findByUserid(userid));
        return new  ModelMapper().map(walletdata, new TypeToken<List<WalletDTO>>(){}.getType());
    }

    @Override
    public long getTotalBalance(String userid) {
        List<WalletEntity> walletdata = walletRepository.findAllByUser(userRepository.findByUserid(userid));
        if(walletdata == null)
        {
            return 0l;
        }
        long totalbalance=0;
        for(WalletEntity walletEntity : walletdata){
            totalbalance+= walletEntity.getBalance();
        }


        return totalbalance;
    }

    @Override
    public WalletDTO updateWalletData(String userid, String walletid, WalletDTO walletDTO) {
        WalletEntity data = walletRepository.findByWalletid(walletid);
        if(data == null)
        {
            return null;
        }
        //update nohp or balance
        data.setPhonenumber(walletDTO.getPhonenumber());
        data.setBalance(walletDTO.getBalance());
        WalletEntity updated = walletRepository.save(data);

        return new ModelMapper().map(updated, WalletDTO.class);
    }


}
