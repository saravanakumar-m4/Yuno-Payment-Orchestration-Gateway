package com.globalstream.payment.strategy;
import com.globalstream.payment.dto.PaymentRequest;
import com.globalstream.payment.dto.PaymentResponse;

public interface PaymentProviderStrategy {
    boolean supports(String countryCode, String paymentMethod);
    PaymentResponse process(PaymentRequest request);
    String getProviderName();
}