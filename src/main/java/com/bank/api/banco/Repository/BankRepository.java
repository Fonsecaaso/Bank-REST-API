package com.bank.api.banco.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
        String sql = "select exists(select 1 from account where id=:id)\n";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public void createAccount(String id){
        String sql = "insert into account(id, amount) value(:id, '0')";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }

    public void updateAmount(String id, int amount){
        String sql = "update account set amount=:amount where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("amount", amount);

        namedParameterJdbcTemplate.update(sql, params);

    }

    public int getBalance(String id){
        String sql = "select amount from account where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql,params, Integer.class);
    }

    public void reset() {
        String sql = "DELETE FROM account";
        jdbcTemplate.update(sql);
    }
}
