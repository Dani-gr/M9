package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import es.proyectotaw.banca.bancapp.dto.RolEntityDTO;
import es.proyectotaw.banca.bancapp.dto.RolusuarioEntityDTO;
import es.proyectotaw.banca.bancapp.dto.UsuarioEntityDTO;
import es.proyectotaw.banca.bancapp.entity.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {
    @Autowired
    RolEntityRepository rolEntityRepository;

    public RolEntityDTO buscaNombre(String nombre) {
        RolEntity rol = rolEntityRepository.findByNombre(nombre).orElseThrow(RuntimeException::new);
        return rol.toDTO();
    }

    public List<RolEntityDTO> buscaNombres(String n1, String... nombres) {
        var res = new ArrayList<RolEntityDTO>(nombres.length + 1);
        res.add(buscaNombre(n1));
        for (String nombre : nombres) res.add(buscaNombre(nombre));
        return res;
    }

    public List<RolEntityDTO> buscaNombresPorUsuarioYCif(UsuarioEntityDTO user, Integer cif) {
        var roles = rolEntityRepository.findRolesByUsuarioAndEmpresaByCif(user.toEntity(), cif);
        return roles.stream().map(RolEntity::toDTO).toList();
    }

    public List<RolEntityDTO> buscaNombresPorUsuarioSinEmpresa(UsuarioEntityDTO user) {
        var roles = rolEntityRepository.findRolesByUsuarioNoEmpresa(user.toEntity());
        return roles.stream().map(RolEntity::toDTO).toList();
    }

    public List<String> getNombresRoles(UsuarioEntityDTO user) {
        return user.getRolusuariosById().stream()
                .map(RolusuarioEntityDTO::getRolByIdrol).map(RolEntityDTO::getNombre).toList();
    }
}
