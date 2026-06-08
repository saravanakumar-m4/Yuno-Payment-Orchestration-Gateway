package com.globalstream.payment.strategy.impl;

import com.globalstream.payment.dto.PaymentRequest;
import com.globalstream.payment.dto.PaymentResponse;
import com.globalstream.payment.strategy.PaymentProviderStrategy;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class StripeStrategy implements PaymentProviderStrategy {

    @Override
    public boolean supports(String countryCode, String paymentMethod) {
        // Fallback / North America / Europe
        return countryCode.matches("US|CA|UK|DE");
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        return PaymentResponse.builder()
                .transactionId("STRIPE-" + UUID.randomUUID().toString())
                .status("APPROVED")
                .processorUsed(getProviderName())
                .message("Processed via Stripe")
                .build();
    }

    @Override
    public String getProviderName() {
        return "Stripe-Global";
    }
}