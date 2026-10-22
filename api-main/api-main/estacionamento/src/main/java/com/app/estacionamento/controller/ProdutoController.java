package com.app.estacionamento.controller;

import com.app.estacionamento.dto.ProdutoRequestDTO;
import com.app.estacionamento.dto.ProdutoResponseDTO;
import com.app.estacionamento.model.Produto;
import com.app.estacionamento.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar(@RequestParam(required = false) String nome) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarNome(nome)
                .stream()
                .map(ProdutoResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ProdutoResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@Valid @RequestBody ProdutoRequestDTO produtoDTO) {
        Produto novoProduto = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.fromEntity(novoProduto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id,
                                                              @RequestBody ProdutoRequestDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO)
                .map(ProdutoResponseDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean removido = produtoService.excluirProduto(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
