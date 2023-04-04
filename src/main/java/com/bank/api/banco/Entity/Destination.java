package com.bank.api.banco.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Destination {
    private String id;
    private int balance;
}
