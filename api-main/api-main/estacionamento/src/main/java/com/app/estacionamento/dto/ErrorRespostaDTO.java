package com.app.estacionamento.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorRespostaDTO {

    int status;
    String mensagem;
    LocalDateTime timetemp;
    Map<String, String> erroCampos;

    public ErrorRespostaDTO(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timetemp = timetemp;
        this.erroCampos = null;
    }

    public ErrorRespostaDTO(int status, String mensagem, Map<String, String> erroCampos, LocalDateTime timetemp) {
        this.status = status;
        this.mensagem = mensagem;
        this.erroCampos = erroCampos;
        this.timetemp = timetemp;
    }

    public ErrorRespostaDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getTimetemp() {
        return timetemp;
    }

    public void setTimetemp(LocalDateTime timetemp) {
        this.timetemp = timetemp;
    }

    public Map<String, String> getErroCampos() {
        return erroCampos;
    }

    public void setErroCampos(Map<String, String> erroCampos) {
        this.erroCampos = erroCampos;
    }
}
