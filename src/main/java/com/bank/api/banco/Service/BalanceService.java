package com.bank.api.banco.Service;

import com.bank.api.banco.Entity.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    public ResponseEntity<?> postEvent(Request req) {
        if (req.getType().equals("deposit"))
            return deposit(req);
        if (req.getType().equals("withdraw"))
            return withdraw(req);
        if (req.getType().equals("transfer"))
            return transfer(req);

        return new ResponseEntity<>("0", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> transfer(Request req) {
        /*
            1. check if accounts exist
            2. if not exists return 404
            3. remove amount from origin
            4. add amount to destination
            5. search balance
         */
        return null;
    }

    private ResponseEntity<?> withdraw(Request req) {
        /*
            1. check if account exist
            2. if not exists return 404
            3. remove amount
            4. search balance
         */
        return null;
    }

    private ResponseEntity<?> deposit(Request req) {
        /*
            1. check if account exist
            2. if not exists create account
            3. deposit amount
            4. search balance
         */
        return null;
    }
}
