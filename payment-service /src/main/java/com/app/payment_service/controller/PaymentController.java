package com.app.payment_service.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/process")
    public String process(@RequestBody Map<String, String> body) {

        String bookingId = body.get("booking_id");

        String walletUrl = System.getenv("WALLET_URL");
        if (walletUrl == null) {
            walletUrl = "http://localhost:8081";
        }

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(
                walletUrl + "/wallet/credit",
                body,
                String.class
        );

        return "Payment successful for booking " + bookingId;
    }
}
