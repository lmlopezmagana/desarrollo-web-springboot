package com.openwebinars.webdata.utils;

import com.openwebinars.webdata.exception.ProductoNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private String getPathForNotFoundExceptionType(EntityNotFoundException ex) {

        if (ex instanceof ProductoNotFoundException productoNotFoundException) {
            return "redirect:/producto/";
        }
        return "notfound";
    }


    @ExceptionHandler(EntityNotFoundException.class)
    //public String notFound(EntityNotFoundException ex) {
    public String notFound(EntityNotFoundException ex, RedirectAttributes redirectAttributes) {
        //return "notfound";
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return getPathForNotFoundExceptionType(ex);
    }

}
