package com.openwebinars.webdata.exception;

import jakarta.persistence.EntityNotFoundException;

public class ProductoNotFoundException extends EntityNotFoundException {

    public ProductoNotFoundException() {
        super("Product not found");
    }

    public ProductoNotFoundException(Long id) {
        super("Product with ID: %d not found".formatted(id));
    }

    public ProductoNotFoundException(String msg) {
        super(msg);
    }

}
