package com.barbearia.enums;


public enum Perfil {
    ADMINISTRADOR("ADMINISTRADOR"),
    CLIENTE("CLIENTE"),
    BARBEIRO("BARBEIRO");

    private String descricao;

    Perfil(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
