package org.bsim.intern.walletdemo.ui.controller;


import org.bsim.intern.walletdemo.service.iservice.IWalletService;
import org.bsim.intern.walletdemo.shared.dto.WalletDTO;
import org.bsim.intern.walletdemo.ui.model.request.WalletRequest;
import org.bsim.intern.walletdemo.ui.model.response.WalletRespose;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    @Autowired
    IWalletService walletService;


    @PostMapping(path = "/{userid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletRespose addNewWallet(@PathVariable String userid,@RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();

        WalletDTO walletDTO = mapper.map(walletRequest, WalletDTO.class);
        WalletDTO createdWallet = walletService.addNewWallet(userid, walletDTO);
        return mapper.map(createdWallet, WalletRespose.class);
    }

    @GetMapping(path = "/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<WalletRespose> getAllWallet(@PathVariable String userid){
        List<WalletDTO> walletsData = walletService.getAllWalletData(userid);

        return new ModelMapper().map(walletsData, new TypeToken<List<WalletRespose>>(){}.getType());
    }

    @GetMapping(path = "/{userid}/balance", produces = {MediaType.APPLICATION_JSON_VALUE})
    public long getBalance(@PathVariable String userid){
        long totalbalance = walletService.getTotalBalance(userid);

        return totalbalance;
    }

    @PutMapping(path = "/{userid}/{walletid}", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletRespose updatewallet (@PathVariable String userid,
                                       @PathVariable String walletid,
                                       @RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();
        WalletDTO walletDTO = mapper.map(walletRequest, WalletDTO.class);
        WalletDTO updatewallet = walletService.updateWalletData(userid,walletid,walletDTO);

        return mapper.map(updatewallet, WalletRespose.class);
    }

}
