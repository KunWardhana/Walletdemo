package org.bsim.intern.walletdemo.service.impl;

import org.bsim.intern.walletdemo.io.entity.TransactionEntity;
import org.bsim.intern.walletdemo.io.entity.WalletEntity;
import org.bsim.intern.walletdemo.io.repository.TransactionRepository;
import org.bsim.intern.walletdemo.io.repository.WalletRepository;
import org.bsim.intern.walletdemo.service.iservice.IServiceTransaction;
import org.bsim.intern.walletdemo.shared.dto.TransactionDTO;
import org.bsim.intern.walletdemo.shared.util.GenerateRandomPublicID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements IServiceTransaction {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final GenerateRandomPublicID generateRandomPublicID;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WalletRepository walletRepository, GenerateRandomPublicID generateRandomPublicID) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.generateRandomPublicID = generateRandomPublicID;
    }


    @Override
    public List<TransactionDTO> getAllTransaction() {
        ModelMapper mapper = new ModelMapper();
        List<TransactionDTO> returnvalue = new ArrayList<>();

        List<TransactionEntity> transactionEntities = transactionRepository.findAll();

        for (TransactionEntity entity: transactionEntities
             )
        {
            returnvalue.add(mapper.map(entity, TransactionDTO.class));
        }

        return returnvalue;
    }

    @Override
    public TransactionDTO addNewTransaction(String walletid, TransactionDTO transactionDTO) {
        ModelMapper mapper = new ModelMapper();
        WalletEntity walletEntity = walletRepository.findByWalletid(walletid);

        TransactionEntity entity = mapper.map(transactionDTO, TransactionEntity.class );
        entity.setWalletEntity(walletEntity);
        entity.setTransactionID(generateRandomPublicID.generateUserId(35));

        TransactionEntity storedvalue = transactionRepository.save(entity);
        TransactionDTO returnvalue = mapper.map(storedvalue, TransactionDTO.class);

        return returnvalue;
    }

    @Override
    public List<TransactionDTO> getAllTransactionByWalletID(String walletid) {
        ModelMapper mapper = new ModelMapper();

        WalletEntity walletEntity = walletRepository.findByWalletid(walletid);
        List<TransactionDTO> returnvalue = new ArrayList<>();
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByWalletEntity(walletEntity);

        for (TransactionEntity entity: transactionEntities
             )
        {
            returnvalue.add(mapper
            .map(entity, TransactionDTO.class));
        }
        
        return returnvalue;
    }

    @Override
    public TransactionDTO updateTransactionByTransactionID(String walletid, String transactionid, TransactionDTO transactionDTO) {
        ModelMapper mapper= new ModelMapper();

        long balanceawal = 0;
        long amountinit = 0;


        WalletEntity walletEntity = walletRepository.findByWalletid(walletid);
        balanceawal = walletEntity.getBalance();

        TransactionEntity entities = transactionRepository.findByTransactionID(transactionid);
        amountinit=entities.getAmount();

        long updated = transactionDTO.getAmount();

        if(amountinit - updated > 0){
            walletEntity.setBalance(balanceawal + (updated-amountinit));
        }
        else
        {
            walletEntity.setBalance(balanceawal - (updated-amountinit));
        }

        TransactionEntity transactionEntity = transactionRepository.findByTransactionID(transactionid);
        TransactionEntity entity = mapper.map(transactionDTO, TransactionEntity.class);
        entity.setWalletEntity(walletEntity);
        entity.setId(transactionEntity.getId());
        entity.setTransactionID(transactionEntity.getTransactionID());

        TransactionEntity stored = transactionRepository.save(entity);

        return mapper.map(stored, TransactionDTO.class);
    }

    @Override
    public TransactionDTO deleteTransaction(String walletid, String transactionid) {
        WalletEntity walletEntity = walletRepository.findByWalletid(walletid);
        TransactionEntity transactionEntity = transactionRepository.findByTransactionID(transactionid);
        transactionEntity.setWalletEntity(walletEntity);
        transactionEntity.setDeleted(true);

        ModelMapper mapper = new ModelMapper();
        TransactionEntity stored = transactionRepository.save(transactionEntity);

        return mapper.map(stored, TransactionDTO.class);

    }
}
