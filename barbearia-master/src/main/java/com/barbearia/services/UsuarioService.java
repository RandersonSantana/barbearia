package com.barbearia.services;

import com.barbearia.dtos.UsuarioDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Usuario;
import com.barbearia.repositories.UsuarioRepository;
import com.barbearia.spec.UsuarioSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private static final String MSG_CLIENTE = "Usuario não encontrado";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioSpec usuarioSpec;

    @Transactional(readOnly = true)
    public UsuarioDTO getUsuarioById(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));

        return converterUsuarioParaUsuarioDTO(usuario);
    }

    public UsuarioDTO converterUsuarioParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPerfis(usuario.getPerfis());
        return usuarioDTO;
    }

    public Usuario converterUsuarioDTOParaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPerfis(usuarioDTO.getPerfis());
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAllUsuario(){
        List<Usuario> result = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTO = new ArrayList<>();
        for (Usuario usuario : result){
            usuarioDTO.add(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getPerfis()));
        }
        return usuarioDTO;
    }

    @Transactional
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO){

        usuarioDTO.setId(null);

        Usuario UsuarioEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        usuarioSpec.verificarSeExisteUsuarioComEmailDuplicado(UsuarioEmail);

        Usuario usuario = converterUsuarioDTOParaUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));

        usuarioSpec.verificarEmailEmUso(usuario, usuarioDTO);
        usuarioSpec.verificarCampoIdNulo(usuarioDTO.getId());

        usuario= converterUsuarioDTOParaUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDTO(usuario);
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
