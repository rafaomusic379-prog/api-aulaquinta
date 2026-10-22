package com.app.estacionamento;

import com.app.estacionamento.dto.ProdutoRequestDTO;
import com.app.estacionamento.model.Produto;
import com.app.estacionamento.repository.ProdutoRepository;
import com.app.estacionamento.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar produto com sucesso quando dados forem válidos")
    void deveCadastrarProdutoComSucesso() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Teclado Mecânico", 250.0);
        Produto cadastrado = produtoService.cadastrarProduto(dto);

        assertNotNull(cadastrado.getId());
        assertEquals("Teclado Mecânico", cadastrado.getNome());
        assertEquals(250.0, cadastrado.getPreco());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com nome menor que 3 letras")
    void deveLancarExcecaoNomeInvalido() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Oi", 10.0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrarProduto(dto));
        assertEquals("O produto deve ter no mínimo 3 letras.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com preço menor ou igual a zero")
    void deveLancarExcecaoPrecoInvalido() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Mouse Pad", 0.0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrarProduto(dto));
        assertEquals("O preço do produto deve ser maior que R$0", ex.getMessage());
    }

    @Test
    @DisplayName("Deve buscar produtos pelo nome corretamente")
    void deveBuscarPorNome() {
        produtoService.cadastrarProduto(new ProdutoRequestDTO("Cadeira Gamer", 800.0));
        produtoService.cadastrarProduto(new ProdutoRequestDTO("Mesa de Escritório", 450.0));

        List<Produto> resultado = produtoService.buscarNome("cadeira");
        assertEquals(1, resultado.size());
        assertEquals("Cadeira Gamer", resultado.get(0).getNome());
    }

    @Test
    @DisplayName("Deve atualizar produto existente")
    void deveAtualizarProduto() {
        Produto produto = produtoService.cadastrarProduto(new ProdutoRequestDTO("Monitor 24", 700.0));
        ProdutoRequestDTO atualizacao = new ProdutoRequestDTO("Monitor 27 Ultra", 950.0);

        Optional<Produto> atualizado = produtoService.atualizarProduto(produto.getId(), atualizacao);

        assertTrue(atualizado.isPresent());
        assertEquals("Monitor 27 Ultra", atualizado.get().getNome());
        assertEquals(950.0, atualizado.get().getPreco());
    }

    @Test
    @DisplayName("Deve excluir produto com sucesso")
    void deveExcluirProduto() {
        Produto produto = produtoService.cadastrarProduto(new ProdutoRequestDTO("Headset", 150.0));
        boolean excluido = produtoService.excluirProduto(produto.getId());

        assertTrue(excluido);
        assertTrue(produtoRepository.findById(produto.getId()).isEmpty());
    }
}
