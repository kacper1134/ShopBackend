package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

// Abstract base class for discounts
public abstract class Discount {
    protected static final Integer priority = 0;
    public abstract Receipt applyDiscount(Receipt receipt);
    public abstract boolean shouldApply(Receipt receipt);
    public Integer getPriority() {
        return priority;
    }
}
