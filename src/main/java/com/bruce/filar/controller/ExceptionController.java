package com.bruce.filar.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String defaultException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        System.out.println("------全局异常----------");
        return "error";
    }
}
