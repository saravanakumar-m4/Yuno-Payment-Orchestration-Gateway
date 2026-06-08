package com.globalstream.payment.strategy.impl;
import com.globalstream.payment.dto.PaymentRequest;
import com.globalstream.payment.dto.PaymentResponse;
import com.globalstream.payment.strategy.PaymentProviderStrategy;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class FlutterwaveStrategy implements PaymentProviderStrategy {

    @Override
    public boolean supports(String countryCode, String paymentMethod) {
        // Routes African volume to Flutterwave to improve the 58% approval rate
        return countryCode.matches("NG|KE|ZA");
    }

    @Override
    public PaymentResponse process(PaymentRequest request) {
        // Mock Flutterwave API Call
        return PaymentResponse.builder()
                .transactionId("FLW-" + UUID.randomUUID().toString())
                .status("APPROVED")
                .processorUsed(getProviderName())
                .message("Processed locally via Flutterwave")
                .build();
    }

    @Override
    public String getProviderName() {
        return "Flutterwave-Africa";
    }
}