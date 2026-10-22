package com.app.estacionamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório e não pode conter apenas espaços.")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres.")
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório.")
    @Positive(message = "O preço deve ser estritamente maior que zero.")
    private Double preco;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
