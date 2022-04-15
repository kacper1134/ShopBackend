package com.virtuslab.internship.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuslab.internship.api.dto.ReceiptEntryDTO;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BasketControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturn404IfProductDoesNotExistInDatabase() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<ReceiptEntryDTO> body = new ArrayList<>();

        body.add(new ReceiptEntryDTO("Breada", 2));
        mvc.perform(MockMvcRequestBuilders.post("/post/basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnCorrectTotalPrice() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<ReceiptEntryDTO> body = new ArrayList<>();
        var productDb = new ProductDb();
        var apple = productDb.getProduct("Apple");
        var bread = productDb.getProduct("Bread");

        body.add(new ReceiptEntryDTO(apple.name(), 2));
        body.add(new ReceiptEntryDTO(bread.name(), 3));
        body.add(new ReceiptEntryDTO(bread.name(), 3));

        var expectedTotalPrice = apple.price().multiply(BigDecimal.valueOf(2)).
                add(bread.price().multiply(BigDecimal.valueOf(6)));

        mvc.perform(MockMvcRequestBuilders.post("/post/basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is(expectedTotalPrice.intValue())));
    }
}
