package com.globalstream.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String transactionId;
    private String status;
    private String processorUsed;
    private String message;
}