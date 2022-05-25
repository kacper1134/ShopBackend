package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

// Error in shouldApply – The value of the compareTo function should be greater or equal to zero
// Changing shouldApply access modifier from private to public
// Rename function apply to applyDiscount and removed use of shouldApply method inside it
public class TenPercentDiscount extends Discount {
    public TenPercentDiscount() {
        super(2);
    }

    @Override
    public Receipt applyDiscount(Receipt receipt) {
        var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
        return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }
}
