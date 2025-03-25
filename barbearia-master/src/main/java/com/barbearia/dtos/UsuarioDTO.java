package com.barbearia.dtos;

import com.barbearia.enums.Perfil;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@Getter
@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private List<Perfil> perfis;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String telefone, String email, List<Perfil> perfis) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.perfis = perfis;
    }
}