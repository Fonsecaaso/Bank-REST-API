package com.bank.api.banco.Entity;

import lombok.Data;

@Data
public class Request {
    private String type;
    private String origin;
    private String destination;
    private String amount;
}
