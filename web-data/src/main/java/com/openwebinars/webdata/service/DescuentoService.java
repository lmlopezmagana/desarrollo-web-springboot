package com.openwebinars.webdata.service;

import com.openwebinars.webdata.model.Producto;
import com.openwebinars.webdata.repos.CategoriaRepository;
import com.openwebinars.webdata.repos.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DescuentoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    /**
     * Aplica un descuento a todos los productos de una categoría
     * @param descuento Un valor decimal entre 0 y 1.
     * @return Lista de Productos modificados
     */
    public List<Producto> aplicarDescuentoCategoria(double descuento, Long idCategoria) {
        List<Producto> productos = productoRepository.findByCategoriaId(idCategoria);

        if (descuento <= 0 || descuento > 1)
            throw new RuntimeException("El descuento no puede ser ni menor que 0 ni mayor que 1");

        return productoRepository.saveAll(
                productos.stream()
                 .map(producto -> {
                    producto.setPrecio(producto.getPrecio() * (1.0 - descuento));
                    return producto;
                 })
                .toList()
        );

    }

    public List<Producto> aplicarDescuentoCategorias(double descuento, List<Long> idsCategorias) {
        List<Producto> result = new ArrayList<>();
        idsCategorias.forEach(id -> {
            List<Producto> l = aplicarDescuentoCategoria(descuento, id);
            result.addAll(l);
        });
        return result;
    }

}
