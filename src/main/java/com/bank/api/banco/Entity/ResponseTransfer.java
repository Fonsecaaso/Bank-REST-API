package com.bank.api.banco.Entity;

import lombok.Data;

@Data
public class ResponseTransfer {
    private Destination origin;
    private Destination destination;
}
