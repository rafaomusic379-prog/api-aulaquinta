package com.app.estacionamento.service;

import com.app.estacionamento.controller.ProdutoDTO;
import com.app.estacionamento.dto.ProdutoRequestDTO;
import com.app.estacionamento.dto.ProdutoResponseDTO;
import com.app.estacionamento.exception.RecursoNaoEncontradoExecption;
import com.app.estacionamento.model.Produto;
import com.app.estacionamento.repository.ProdutoRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream().map(Produto -> new ProdutoResponseDTO(Produto.getId(),Produto.getNome(),Produto.getPreco())).toList();
    }

    public List<Produto> buscarNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            return produtoRepository.findByNomeContainingIgnoreCase(nome.trim());
        }
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExecption("produto nao encontrado" + id));
    }

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = new Produto(null,produtoDTO.getNome(),produtoDTO.getPreco());
        Produto novoProduto = produtoRepository.save(produto);

        return new ProdutoResponseDTO(novoProduto.getId(),novoProduto.getNome(),novoProduto.getPreco());

    }

    // Sobrecarga para compatibilidade com ProdutoDTO legado
    public Produto cadastrarProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO == null) {
            throw new IllegalArgumentException("Os dados do produto não podem ser nulos.");
        }
        return cadastrarProduto(new ProdutoRequestDTO(produtoDTO.getNome(), produtoDTO.getPreco()));
    }

    public Optional<Produto> atualizarProduto(Long id, ProdutoRequestDTO produtoDTO) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            if (produtoDTO.getPreco() != null) {
                if (produtoDTO.getPreco() <= 0) {
                    throw new IllegalArgumentException("O preço do produto deve ser maior que R$0");
                }
                produtoExistente.setPreco(produtoDTO.getPreco());
            }

            if (produtoDTO.getNome() != null) {
                if (produtoDTO.getNome().trim().length() < 3) {
                    throw new IllegalArgumentException("O produto deve ter no mínimo 3 letras.");
                }
                produtoExistente.setNome(produtoDTO.getNome().trim());
            }

            return produtoRepository.save(produtoExistente);
        });
    }

    public boolean excluirProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
