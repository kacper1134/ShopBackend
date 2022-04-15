package com.virtuslab.internship.api.service;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.util.List;

public interface IBasketService {
    Receipt getReceipt(List<ReceiptEntry> entries);
}
