package com.bank.api.banco.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ResponseDeposit {
    private Destination destination;
}
