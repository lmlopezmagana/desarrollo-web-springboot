package com.openwebinars.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

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



}
