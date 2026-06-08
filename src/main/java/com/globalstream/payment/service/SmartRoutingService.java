package com.globalstream.payment.service;
import com.globalstream.payment.dto.PaymentRequest;
import com.globalstream.payment.dto.PaymentResponse;
import com.globalstream.payment.entity.TransactionRecord;
import com.globalstream.payment.repository.TransactionRepository;
import com.globalstream.payment.strategy.PaymentProviderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SmartRoutingService {

    private final List<PaymentProviderStrategy> providers;
    private final TransactionRepository transactionRepository;

    @Autowired
    public SmartRoutingService(List<PaymentProviderStrategy> providers, TransactionRepository transactionRepository) {
        this.providers = providers;
        this.transactionRepository = transactionRepository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        // 1. Smart Routing Logic: Find the best provider for the region
        PaymentProviderStrategy selectedProvider = providers.stream()
                .filter(p -> p.supports(request.getCountryCode(), request.getPaymentMethod()))
                .findFirst()
                // Default fallback if no specific regional match is found
                .orElseThrow(() -> new RuntimeException("No supported payment provider found for region: " + request.getCountryCode()));

        // 2. Process via the selected provider
        PaymentResponse response = selectedProvider.process(request);

        // 3. Persist the unified transaction record via JPA
        TransactionRecord record = new TransactionRecord();
        record.setId(response.getTransactionId());
        record.setUserId(request.getUserId());
        record.setAmount(request.getAmount());
        record.setCurrency(request.getCurrency());
        record.setCountryCode(request.getCountryCode());
        record.setProcessorRoute(selectedProvider.getProviderName());
        record.setStatus(response.getStatus());
        
        transactionRepository.save(record);

        return response;
    }
}