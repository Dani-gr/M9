package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.RolusuarioEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolusuarioEntityRepository extends JpaRepository<RolusuarioEntity, Integer> {
    @Query("select rolusuariosById.rolByIdrol from RolusuarioEntity rolusuariosById " +
            "where rolusuariosById.usuarioByIdusuario=:user and rolusuariosById.empresaByIdempresa.cif = :cif")
    List<RolEntity> findRolesByUsuarioAndEmpresaByCif(@Param("user") UsuarioEntity user, @Param("cif") String cif);
}