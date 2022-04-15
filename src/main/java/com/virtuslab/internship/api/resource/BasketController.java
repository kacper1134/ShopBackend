package com.virtuslab.internship.api.resource;

import com.virtuslab.internship.api.dto.ReceiptEntryDTO;
import com.virtuslab.internship.api.mapper.ReceiptEntryDTOMapper;
import com.virtuslab.internship.api.service.IBasketService;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BasketController {
    private final ReceiptEntryDTOMapper receiptEntryDTOMapper;
    private final IBasketService basketService;

    @Autowired
    public BasketController(ReceiptEntryDTOMapper receiptEntryDTOMapper, IBasketService basketService) {
        this.receiptEntryDTOMapper = receiptEntryDTOMapper;
        this.basketService = basketService;
    }

    @RequestMapping(value = "/post/basket", method = RequestMethod.POST)
    public ResponseEntity<Receipt> getReceipt(@RequestBody List<ReceiptEntryDTO> entryDTOs) {
        var entries = new ArrayList<ReceiptEntry>();

        try {
            entryDTOs.forEach(entry -> entries.add(receiptEntryDTOMapper.mapToReceiptEntry(entry)));
        }
        catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(basketService.getReceipt(entries), HttpStatus.OK);
    }
}
