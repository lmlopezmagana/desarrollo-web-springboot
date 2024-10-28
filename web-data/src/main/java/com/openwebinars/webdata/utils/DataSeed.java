package com.openwebinars.webdata.utils;

import com.openwebinars.webdata.model.Categoria;
import com.openwebinars.webdata.model.Producto;
import com.openwebinars.webdata.repos.CategoriaRepository;
import com.openwebinars.webdata.repos.ProductoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @PostConstruct
    public void insertData() {

        Categoria categoriaElectronica = Categoria.builder()
                .nombre("Electrónica")
                .build();

        Categoria categoriaHogar = Categoria.builder()
                .nombre("Hogar")
                .build();


        categoriaRepository.save(categoriaElectronica);
        categoriaRepository.save(categoriaHogar);

        Producto producto1 = Producto.builder()
                .nombre("Laptop")
                .precio(1200.99)
                .categoria(categoriaElectronica)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Smartphone")
                .precio(699.50)
                .categoria(categoriaElectronica)
                .build();

        Producto producto3 = Producto.builder()
                .nombre("Auriculares Inalámbricos")
                .precio(59.99)
                .categoria(categoriaElectronica)
                .build();

        Producto producto4 = Producto.builder()
                .nombre("Lámpara de Mesa")
                .precio(29.99)
                .categoria(categoriaHogar)
                .build();

        Producto producto5 = Producto.builder()
                .nombre("Cafetera")
                .precio(99.99)
                .categoria(categoriaHogar)
                .build();

        Producto producto6 = Producto.builder()
                .nombre("Aspiradora")
                .precio(150.00)
                .categoria(categoriaHogar)
                .build();

        List<Producto> productos = List.of(producto1, producto2, producto3, producto4, producto5, producto6);

        productoRepository.saveAll(productos);


    }

}
