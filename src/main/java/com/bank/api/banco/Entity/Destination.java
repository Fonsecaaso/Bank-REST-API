package com.bank.api.banco.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Destination {
    private String id;
    private int balance;
}
