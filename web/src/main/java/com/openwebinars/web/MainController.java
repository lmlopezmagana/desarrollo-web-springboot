package com.openwebinars.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductoRepository productoRepository;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("message", "Hola Mundo!");
        return "index";
    }

    @GetMapping("/index")
    public ModelAndView welcomeV2() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Hola Mundo!");
        return modelAndView;
    }

    @ModelAttribute("otroSaludo")
    public String otroSaludo() {
        return "Espero aprender mucho con este curso de Desarrollo web con Spring Boot";
    }

    @GetMapping("/other1")
    @ResponseBody
    public String responseString() {
        return "Hola Mundo!!!";
    }


    @GetMapping("/other2")
    public ResponseEntity<String> responseEntity() {
        return ResponseEntity.ok("Hola Mundo!!!");
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/";
    }

    @GetMapping("/forward")
    public String forward() {
        return "forward:/";
    }

    @GetMapping("/producto/{id:[0-9]+}")
    public String ejemploPathVariable(
            @PathVariable("id") Long id,
            Model model) {
        model.addAttribute("id", id);
        return "producto";
    }

    @GetMapping("/saludo")
    public String ejemploQueryParam(
            @RequestParam(
                    name = "nombre",
                    required = false,
                    defaultValue = "User") String nombre,
            Model model) {
        String msg = "Hola, " + nombre;
        model.addAttribute("message", msg);
        return "index";
    }


    @GetMapping("/multimap")
    public ResponseEntity<String> ejemploMultiValueMap(
            @RequestParam MultiValueMap<String, String> params) {
        return ResponseEntity.ok(params.toString());
    }

    @GetMapping("/tips")
    public String tipsThymeleaf(Model model) {
        /*Producto producto = Producto.builder()
                .id(7L)
                .nombre("iPhone 16 Pro")
                .precio(1219.24)
                .build();
        model.addAttribute("producto", producto);*/
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("fecha",
                LocalDateTime.of(2025, 1, 1, 10, 15));
        return "tips";
    }




}
