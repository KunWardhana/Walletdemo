package org.bsim.intern.walletdemo.service.iservice;

import org.bsim.intern.walletdemo.shared.dto.WalletDTO;

import java.util.List;

public interface IWalletService {
    WalletDTO addNewWallet (String userid, WalletDTO walletDTO);

    List<WalletDTO> getAllWalletData (String userid);

    long getTotalBalance(String userid);

    WalletDTO updateWalletData(String userid, String walletid, WalletDTO walletDTO);
}
