package com.virtuslab.internship.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceiptEntryDTO {
    private String name;
    private Integer quantity;
}
