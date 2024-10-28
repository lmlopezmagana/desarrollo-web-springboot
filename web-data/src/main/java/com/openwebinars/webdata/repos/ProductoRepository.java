package com.openwebinars.webdata.repos;

import com.openwebinars.webdata.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByPrecioBetween(double startPrecio, double endPrecio);
    List<Producto> findByCategoriaId(Long id);
    List<Producto> findByNombreIgnoreCase(String nombre);


}
