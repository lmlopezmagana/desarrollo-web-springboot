package com.openwebinars.web;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoRepository {

    public List<Producto> findAll() {
        return List.of(
                new Producto(1L, "Laptop", 1200.99, true),
                new Producto(2L, "Smartphone", 699.50, false),
                new Producto(3L, "Auriculares Inalámbricos", 59.99, false),
                new Producto(4L, "Televisor 4K", 899.00, true),
                new Producto(5L, "Cámara Fotográfica", 450.75, false),
                new Producto(6L, "Tablet", 329.99, false)
        );
    }

}
