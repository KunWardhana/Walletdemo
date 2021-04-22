package org.bsim.intern.walletdemo.service.iservice;

import org.bsim.intern.walletdemo.shared.dto.TransactionDTO;

import java.util.List;

public interface IServiceTransaction {
    List<TransactionDTO> getAllTransaction();

    TransactionDTO addNewTransaction(String walletid, TransactionDTO transactionDTO);

    List<TransactionDTO> getAllTransactionByWalletID(String walletid);

    TransactionDTO updateTransactionByTransactionID(String walletid, String transactionid, TransactionDTO transactionDTO);

    TransactionDTO deleteTransaction(String walletid, String transactionid);
}
