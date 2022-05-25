package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllDiscountsTest {
    @Test
    void shouldNotApplyDiscount() {
        // Given
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        var receipt = new Receipt(receiptEntries);
        var firstDiscount = new FifteenPercentDiscount();
        var secondDiscount = new TenPercentDiscount();
        var expectedTotalPrice = BigDecimal.ZERO;

        // When
        receipt.addDiscount(firstDiscount);
        receipt.addDiscount(secondDiscount);
        var receiptAfterDiscounts = receipt.applyAllDiscounts();

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(0, receiptAfterDiscounts.discounts().size());
    }

    @Test
    void shouldApplyTenPercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(steak, 3));

        var receipt = new Receipt(receiptEntries);
        var firstDiscount = new FifteenPercentDiscount();
        var secondDiscount = new TenPercentDiscount();
        var expectedTotalPrice = steak.price().multiply(BigDecimal.valueOf(3)).add(bread.price()).
                multiply(BigDecimal.valueOf(0.9));

        // When
        receipt.addDiscount(firstDiscount);
        receipt.addDiscount(secondDiscount);
        var receiptAfterDiscounts = receipt.applyAllDiscounts();

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(1, receiptAfterDiscounts.discounts().size());
    }

    @Test
    void shouldApplyOnlyFifteenPercentDiscountBecauseAfterFirstDiscountPriceIsLessThan50() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 11));

        var receipt = new Receipt(receiptEntries);
        var firstDiscount = new FifteenPercentDiscount();
        var secondDiscount = new TenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(11)).multiply(BigDecimal.valueOf(0.85));

        // When
        receipt.addDiscount(secondDiscount);
        receipt.addDiscount(firstDiscount);
        var receiptAfterDiscounts = receipt.applyAllDiscounts();

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(1, receiptAfterDiscounts.discounts().size());
    }

    @Test
    void shouldApplyFifteenAndTenPercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 3));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var firstDiscount = new FifteenPercentDiscount();
        var secondDiscount = new TenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(3)).add(steak.price()).
                multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        // When
        receipt.addDiscount(firstDiscount);
        receipt.addDiscount(secondDiscount);
        var receiptAfterDiscounts = receipt.applyAllDiscounts();

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(2, receiptAfterDiscounts.discounts().size());
    }
}
