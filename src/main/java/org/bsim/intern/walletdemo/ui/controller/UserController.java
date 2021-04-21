package org.bsim.intern.walletdemo.ui.controller;

import org.bsim.intern.walletdemo.service.iservice.IUserService;
import org.bsim.intern.walletdemo.shared.dto.UserDTO;
import org.bsim.intern.walletdemo.ui.model.request.UserRequest;
import org.bsim.intern.walletdemo.ui.model.response.UserRespond;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserRespond> getUsers(){
        List<UserDTO> users = userService.getListUser();
        List<UserRespond> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for(UserDTO userDTO : users){
            value.add(mapper.map(userDTO, UserRespond.class));
        }

        return value;
    }

    @GetMapping(path = "/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRespond getUserByName(@PathVariable String username){
        UserDTO getUser = userService.getUserbyusername(username);
        if(getUser==null)
        {
            return null;
        }

        return new ModelMapper().map(getUser, UserRespond.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRespond addnewuser(@RequestBody UserRequest user){
        ModelMapper mapper = new ModelMapper();
        UserDTO userdto = mapper.map(user, UserDTO.class);
        UserDTO createduser = userService.addNewData(userdto);

        UserRespond response = mapper.map (createduser, UserRespond.class);
        return response;
    }


}
