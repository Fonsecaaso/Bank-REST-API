package com.bank.api.banco;

import com.bank.api.banco.Entity.Destination;
import com.bank.api.banco.Entity.Request;
import com.bank.api.banco.Entity.ResponseDeposit;
import com.bank.api.banco.Repository.BankRepository;
import com.bank.api.banco.Service.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BankServiceTests {
    @InjectMocks
    private BankService bankService;

    @Mock
    BankRepository bankRepository;

    @Test
    public void testaRequisicaoNula(){
        // arrange
        var req = Request.builder().type(" ").build();

        // act
        ResponseEntity<?> res = bankService.postEvent(req);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    public void testaDeposito(){
        // arrange
        var req = Request.builder()
                .type("deposit")
                .destination("12")
                .amount(30).build();

        ResponseDeposit response = ResponseDeposit.builder().destination(Destination.builder().id("12").balance(30).build()).build();

        when(bankRepository.accountExists(any())).thenReturn(true);

        // act
        ResponseEntity<?> res = bankService.postEvent(req);

        // assert
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(response, res.getBody());
    }

}
