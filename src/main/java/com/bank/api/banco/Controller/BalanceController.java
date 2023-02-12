package com.bank.api.banco.Controller;

import com.bank.api.banco.Entity.Request;
import com.bank.api.banco.Service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("balance")
public class BalanceController {
    @Autowired
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }


    @PostMapping
    public ResponseEntity<?> postEvent(@RequestBody Request req){
        return balanceService.postEvent(req);
    }
}
