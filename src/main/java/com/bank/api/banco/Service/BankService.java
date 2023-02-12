package com.bank.api.banco.Service;

import com.bank.api.banco.Entity.*;
import com.bank.api.banco.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    @Autowired
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public ResponseEntity<?> postEvent(Request req) {
        if (req.getType().equals("deposit"))
            return deposit(req);
        if (req.getType().equals("withdraw"))
            return withdraw(req);
        if (req.getType().equals("transfer"))
            return transfer(req);

        return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getBalance(String accountId) {
        if (accountNotExist(accountId))
            return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(bankRepository.getBalance(accountId),HttpStatus.OK);
    }

    private ResponseEntity<?> transfer(Request req) {
        /*
            1. check if accounts exist
            2. if not exists return 404
            3. remove amount from origin
            4. add amount to destination
            5. search balance
         */
        if (accountNotExist(req.getOrigin()))
            return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);

        if (accountNotExist(req.getDestination()))
            bankRepository.createAccount(req.getDestination());

        int balanceOrigin = bankRepository.getBalance(req.getOrigin());
        balanceOrigin -= req.getAmount();

        bankRepository.updateAmount(req.getOrigin(), balanceOrigin);

        int balanceDestination = bankRepository.getBalance(req.getDestination());
        balanceDestination += req.getAmount();

        bankRepository.updateAmount(req.getDestination(), balanceDestination);


        Destination origin = new Destination(req.getOrigin(),balanceOrigin);
        Destination destination = new Destination(req.getDestination(),balanceDestination);

        ResponseTransfer res = new ResponseTransfer();
        res.setOrigin(origin);
        res.setDestination(destination);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    private ResponseEntity<?> withdraw(Request req) {
        /*
            1. check if account exist
            2. if not exists return 404
            3. search balance
            4. remove amount from previous value (balance)
         */
        if (accountNotExist(req.getOrigin()))
            return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);

        int balance = bankRepository.getBalance(req.getOrigin());
        balance -= req.getAmount();

        bankRepository.updateAmount(req.getOrigin(), balance);
        req.setAmount(balance);

        Destination origin = new Destination(req.getOrigin(),balance);

        ResponseWithdraw res = new ResponseWithdraw();
        res.setOrigin(origin);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    private ResponseEntity<?> deposit(Request req) {
        /*
            1. check if account exist
            2. if not exists create account
            3. search balance
            4. add amount to previous value (balance)
         */

        if (accountNotExist(req.getDestination()))
            bankRepository.createAccount(req.getDestination());

        int balance = bankRepository.getBalance(req.getDestination());
        balance += req.getAmount();

        bankRepository.updateAmount(req.getDestination(), balance);

        Destination destination = new Destination(req.getDestination(),balance);

        ResponseDeposit res = new ResponseDeposit();
        res.setDestination(destination);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }


    private boolean accountNotExist(String id) {
        return !bankRepository.accountExists(id);
    }

    public ResponseEntity<?> reset() {
        bankRepository.reset();
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}
