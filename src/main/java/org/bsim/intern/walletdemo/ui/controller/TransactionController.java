package org.bsim.intern.walletdemo.ui.controller;


import org.bsim.intern.walletdemo.io.entity.TransactionEntity;
import org.bsim.intern.walletdemo.service.iservice.IServiceTransaction;
import org.bsim.intern.walletdemo.shared.dto.TransactionDTO;
import org.bsim.intern.walletdemo.ui.model.request.TransactionRequest;
import org.bsim.intern.walletdemo.ui.model.response.TransactionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final IServiceTransaction iServiceTransaction;


    public TransactionController(IServiceTransaction iServiceTransaction) {
        this.iServiceTransaction = iServiceTransaction;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getAllTransaction(){
        ModelMapper mapper = new ModelMapper();
        List<TransactionResponse> returnvalue = new ArrayList<>();

        List<TransactionDTO> transactionDTOS = iServiceTransaction.getAllTransaction();
        for (TransactionDTO dto: transactionDTOS
             ) {
            returnvalue.add(mapper.map(dto, TransactionResponse.class));
        }

        return returnvalue;
    }

    @PostMapping(path = "/{walletid}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse addNewTransaction(@PathVariable String walletid,
                                                 @RequestBody TransactionRequest transactionRequest){
        ModelMapper mapper = new ModelMapper();

        TransactionDTO transactionDTO = mapper.map(transactionRequest, TransactionDTO.class);
        TransactionDTO storedvalue = iServiceTransaction.addNewTransaction(walletid, transactionDTO);
        TransactionResponse returnvalue = mapper.map(storedvalue, TransactionResponse.class);


        return returnvalue;
    }

    @GetMapping(path = "/{walletid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getAllTransactionByWalletID(@PathVariable String walletid){
        ModelMapper mapper = new ModelMapper();
        List<TransactionResponse> returnvalue = new ArrayList<>();
        List<TransactionDTO> allTransaction = iServiceTransaction.getAllTransactionByWalletID(walletid);
        for (TransactionDTO dto: allTransaction
             ) {
            returnvalue.add(mapper.map(dto, TransactionResponse.class));
            
        }

        return returnvalue;
    }

    @PutMapping(path = "/{walletid}/{transactionid}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse updateTransactionByTransactionID(@PathVariable String walletid,
                                                               @PathVariable String transactionid, @RequestBody TransactionRequest transactionRequest){
        ModelMapper mapper = new ModelMapper();
        TransactionDTO transactionDTO = mapper.map(transactionRequest, TransactionDTO.class);
        TransactionDTO fetchdata = iServiceTransaction.updateTransactionByTransactionID(walletid, transactionid, transactionDTO);
        TransactionResponse returnvalue = mapper.map(fetchdata, TransactionResponse.class);
        return returnvalue;
    }

    @DeleteMapping(path = "/{walletid}/{transactionid}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse deleteTransaction(@PathVariable String walletid,
                                                 @PathVariable String transactionid)
    {
        ModelMapper mapper = new ModelMapper();
        TransactionDTO transactionDTO = iServiceTransaction.deleteTransaction(walletid, transactionid);
        return mapper.map(transactionDTO, TransactionResponse.class);
    }

}
