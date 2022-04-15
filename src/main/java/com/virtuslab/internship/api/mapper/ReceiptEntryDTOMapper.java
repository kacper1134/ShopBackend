package com.virtuslab.internship.api.mapper;

import com.virtuslab.internship.api.dto.ReceiptEntryDTO;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiptEntryDTOMapper {
    private final ProductDb productDb;

    @Autowired
    public ReceiptEntryDTOMapper(ProductDb productDb) {
        this.productDb = productDb;
    }

    public ReceiptEntry mapToReceiptEntry(ReceiptEntryDTO receiptEntryDTO) {
        return new ReceiptEntry(productDb.getProduct(receiptEntryDTO.getName()), receiptEntryDTO.getQuantity());
    }
}
