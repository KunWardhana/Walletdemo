package org.bsim.intern.walletdemo.service.iservice;

import org.bsim.intern.walletdemo.shared.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getListUser();

    //get single value by username
    UserDTO getUserbyusername(String username);

    //bikin user baru
    UserDTO addNewData(UserDTO user);
}
