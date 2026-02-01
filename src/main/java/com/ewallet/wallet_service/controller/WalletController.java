package com.ewallet.wallet_service.controller;

import com.ewallet.wallet_service.entity.Wallet;
import com.ewallet.wallet_service.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    // Get balance
    @GetMapping("/balance/{email}")
    public Double getBalance(@PathVariable String email) {
        return walletRepository.findByUserEmail(email)
                .map(Wallet::getBalance)
                .orElse(0.0);
    }

    // Add money
    @PostMapping("/add/{email}/{amount}")
    public String addMoney(@PathVariable String email,
                           @PathVariable Double amount) {

        Wallet wallet = walletRepository.findByUserEmail(email)
                .orElse(new Wallet());

        wallet.setUserEmail(email);
        wallet.setBalance(
                (wallet.getBalance() == null ? 0.0 : wallet.getBalance()) + amount
        );

        walletRepository.save(wallet);
        return "Money added successfully";
    }
}