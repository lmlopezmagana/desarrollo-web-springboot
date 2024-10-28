package com.openwebinars.webdata.controller;

import com.openwebinars.webdata.model.Categoria;
import com.openwebinars.webdata.model.Producto;
import com.openwebinars.webdata.service.CategoriaService;
import com.openwebinars.webdata.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Log
@Controller
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @ModelAttribute("categorias")
    public List<Categoria> categorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/new")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "form-producto";
    }

    @PostMapping("/submit")
    public String procesarFomularioProducto(
            Model model,
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form-producto";
        }

        productoService.save(producto);
        return "redirect:/producto/list";

    }

    @GetMapping({"/","/list"})
    public String list(Model model) {
        List<Producto> productos = productoService.findAll();
        log.info(productos.toString());
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEditarProducto(
            @PathVariable Long id,
            Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "form-producto";
    }


}

