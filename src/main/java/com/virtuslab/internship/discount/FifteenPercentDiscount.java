package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount extends Discount {
    public FifteenPercentDiscount() {
        super(1);
    }

    @Override
    public Receipt applyDiscount(Receipt receipt) {
        var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
        return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        var numberOfGrainProducts =  receipt.entries().stream().
                                             filter(entry -> entry.product().type().equals(Product.Type.GRAINS)).
                                             map(ReceiptEntry::quantity).
                                             reduce(0, Integer::sum);
        return numberOfGrainProducts >= 3;
    }
}
