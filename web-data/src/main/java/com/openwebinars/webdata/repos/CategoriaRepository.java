package com.openwebinars.webdata.repos;

import com.openwebinars.webdata.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
