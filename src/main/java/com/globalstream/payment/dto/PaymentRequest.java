package com.globalstream.payment.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String userId;
    private BigDecimal amount;
    private String currency;
    private String countryCode; // e.g., "NG", "BR", "US"
    private String paymentMethod; // e.g., "CARD", "PIX", "MPESA"
}