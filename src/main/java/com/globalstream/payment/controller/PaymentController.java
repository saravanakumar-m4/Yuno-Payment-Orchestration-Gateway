package com.globalstream.payment.controller;
import com.globalstream.payment.dto.PaymentRequest;
import com.globalstream.payment.dto.PaymentResponse;
import com.globalstream.payment.service.SmartRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final SmartRoutingService orchestrationService;

    @Autowired
    public PaymentController(SmartRoutingService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = orchestrationService.processPayment(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                PaymentResponse.builder()
                    .status("FAILED")
                    .message(e.getMessage())
                    .build()
            );
        }
    }
}