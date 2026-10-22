package com.app.estacionamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OlaController {

    @GetMapping("/ola")
    public String ola() {
        return "Bem vindo";
    }

    @GetMapping("/status")
    public StatusSistemaDTO statusSistemaDTO() {
        return new StatusSistemaDTO(
                "Sistema Senai de Ensino de qualidade",
                "1.0",
                "Rodando a todo vapor",
                5
        );
    }
}
