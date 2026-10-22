package com.app.estacionamento.exception;

import com.app.estacionamento.dto.ErrorRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManipuladorGlobalErro {

    @ExceptionHandler(RecursoNaoEncontradoExecption.class)
    public ResponseEntity<ErrorRespostaDTO> tratarRecursoNaoEncontrado(RecursoNaoEncontradoExecption execption){
        ErrorRespostaDTO errorRespostaDTO = new ErrorRespostaDTO(
                HttpStatus.NOT_FOUND.value(),
                execption.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRespostaDTO);
    }

    public ResponseEntity<ErrorRespostaDTO> tratavalidacoes(MethodArgumentNotValidException ex){
        Map<String, String> mapaDeErros = new HashMap<>();

        for (FieldError erro: ex.getBindingResult().getFieldErrors()){
            mapaDeErros.put(erro.getField(), erro.getDefaultMessage());
        }

        ErrorRespostaDTO reposta = new ErrorRespostaDTO(
                HttpStatus.BAD_REQUEST.value(),
                "erro de validaçao nos dados"

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorRespostaDTO());

    }

}
