package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.Discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Instead of null a new ArrayList object for discounts should be created
// Object discounts is now list of Discounts instead of names of Discount
// Added new methods - addDiscount to add a new discount, and applyAllDiscounts to apply all discounts at once
public record Receipt(
        List<ReceiptEntry> entries,
        List<Discount> discounts,
        BigDecimal totalPrice) {

    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new ArrayList<>(),
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    public void addDiscount(Discount discount) {
        if (discount.shouldApply(this)) {
            discounts.add(discount);
        }
    }

    public Receipt applyAllDiscounts() {
        this.discounts.sort(Comparator.comparing(Discount::getPriority));
        var currentReceipt = this;

        var usedDiscounts = new ArrayList<>(discounts);

        for (var discount: discounts) {
            if (discount.shouldApply(currentReceipt)) {
                currentReceipt = discount.applyDiscount(currentReceipt);
            }
            else {
                usedDiscounts.remove(discount);
            }
        }
        return new Receipt(currentReceipt.entries, usedDiscounts, currentReceipt.totalPrice);
    }
}
