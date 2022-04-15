package com.virtuslab.internship.product;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductDbTest {
    @Test
    void shouldThrowExceptionBecauseProductIsNotInDatabase() {
        var productDb = new ProductDb();

        assertThrows(NoSuchElementException.class, () -> productDb.getProduct("Grass"));
    }
}
