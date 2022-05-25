package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

// Abstract base class for discounts
public abstract class Discount {
    protected  Integer priority;

    public Discount (Integer priority) {
        this.priority = priority;
    }
    public abstract Receipt applyDiscount(Receipt receipt);
    public abstract boolean shouldApply(Receipt receipt);
    public Integer getPriority() {
        return priority;
    }
}
