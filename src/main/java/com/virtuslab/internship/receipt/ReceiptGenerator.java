package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Added products from the shopping cart to the list of entries on the receipt
@Component
public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        basket.getProducts().forEach((product, quantity) -> receiptEntries.add(new ReceiptEntry(product, quantity)));

        return new Receipt(receiptEntries);
    }
}
