package com.bank.api.banco.Controller;

import com.bank.api.banco.Entity.Request;
import com.bank.api.banco.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {
    @Autowired
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @PostMapping
    @RequestMapping("event")
    public ResponseEntity<?> postEvent(@RequestBody Request req){
        return bankService.postEvent(req);
    }

    @GetMapping
    @RequestMapping("balance")
    public ResponseEntity<?> getBalance(@RequestParam String account_id){
        return bankService.getBalance(account_id);
    }

    @PostMapping
    @RequestMapping("reset")
    public ResponseEntity<?> reset(){
        return bankService.reset();
    }
}
