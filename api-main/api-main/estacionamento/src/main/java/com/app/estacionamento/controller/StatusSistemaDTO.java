package com.app.estacionamento.controller;

public class StatusSistemaDTO {

    private String sistema;
    private String versao;
    private String status;
    private int usuariosConectados;

    public StatusSistemaDTO() {
    }

    public StatusSistemaDTO(String sistema, String versao, String status, int usuariosConectados) {
        this.sistema = sistema;
        this.versao = versao;
        this.status = status;
        this.usuariosConectados = usuariosConectados;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUsuariosConectados() {
        return usuariosConectados;
    }

    public void setUsuariosConectados(int usuariosConectados) {
        this.usuariosConectados = usuariosConectados;
    }
}
