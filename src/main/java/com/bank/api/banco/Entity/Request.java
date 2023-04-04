package com.bank.api.banco.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private String type;
    private String origin;
    private String destination;
    private int amount;
}
