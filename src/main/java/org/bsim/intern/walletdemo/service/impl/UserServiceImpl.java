package org.bsim.intern.walletdemo.service.impl;

import org.bsim.intern.walletdemo.io.entity.UserEntity;
import org.bsim.intern.walletdemo.io.repository.UserRepository;
import org.bsim.intern.walletdemo.service.iservice.IUserService;
import org.bsim.intern.walletdemo.shared.dto.UserDTO;
import org.bsim.intern.walletdemo.shared.util.GenerateRandomPublicID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenerateRandomPublicID generateRandomPublicID;

    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        // get all user from database
        List<UserEntity> users = userRepository.findAll();
        //list userentiity --> list UserDTO
        for(UserEntity userEntity : users){
            value.add(mapper.map(userEntity, UserDTO.class));
        }
        return value;
    }

    @Override
    public UserDTO getUserbyusername(String username) {
        UserEntity getUser = userRepository.findByUsername(username);
        if(getUser == null){
            return null;
        }

        return new ModelMapper().map(getUser, UserDTO.class);
    }

    @Override
    public UserDTO addNewData(UserDTO user) {
        user.setUserid(generateRandomPublicID.generateUserId(30));
        ModelMapper mapper = new ModelMapper();
        UserEntity entity = mapper.map(user, UserEntity.class);
        UserEntity storedata = userRepository.save(entity);

        UserDTO value = mapper.map(storedata, UserDTO.class);

        return value;
    }
}
