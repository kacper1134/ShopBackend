package com.virtuslab.internship.api.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService implements IBasketService {
    private final ReceiptGenerator receiptGenerator;

    public BasketService(ReceiptGenerator receiptGenerator) {
        this.receiptGenerator = receiptGenerator;
    }

    @Override
    public Receipt getReceipt(List<ReceiptEntry> entries) {
        var basket = new Basket();
        entries.forEach(entry -> basket.addProduct(entry.product(), entry.quantity()));
        return receiptGenerator.generate(basket);
    }
}
