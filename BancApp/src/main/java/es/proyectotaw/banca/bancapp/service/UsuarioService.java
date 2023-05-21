package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dto.UsuarioEntityDTO;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UsuarioService {
    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;

    public UsuarioEntityDTO creaUsuario(String nif, String nombre, String segundoNombre, String primerApellido,
                                        String segundoApellido, Date sqlFechaNacimiento, String email, String password) {
        UsuarioEntity tmp = new UsuarioEntity();
        tmp.construct(nif, nombre, segundoNombre, primerApellido, segundoApellido, sqlFechaNacimiento, email, password);
        return tmp.toDTO();
    }

    public void guardar(UsuarioEntityDTO... usuarios) {
        for (UsuarioEntityDTO usuario : usuarios) usuarioEntityRepository.save(usuario.toEntity());

    }

    public UsuarioEntityDTO buscaEmail(String email) {
        UsuarioEntity usuarioEntity = usuarioEntityRepository.findByEmailIgnoreCase(email).orElse(null);
        return usuarioEntity == null ? null : usuarioEntity.toDTO();
    }

    public void vaciarBBDD() {
        usuarioEntityRepository.deleteAll();
    }
}
