package com.bank.api.banco.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BankRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /*
    1. account exist
    2. create account
    3. add amount to account
    4. remove amount from account
    5. get balance
     */

    public boolean accountExists(String id){
        return true;
    }

    public void createAccount(String id){

    }

    public void addAmount(String id, int amount){

    }

    public void removeAmount(String id, int amount){

    }

    public int getBalance(String id){
        return 0;
    }
}
