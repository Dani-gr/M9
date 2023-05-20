package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface RolEntityRepository extends JpaRepository<RolEntity, Integer> {
    @Query("select r from RolEntity r where upper(r.nombre) = upper(?1)")
    Optional<RolEntity> findByNombre(@NonNull String nombre);

    @Query("select rolusuariosById.rolByIdrol from RolusuarioEntity rolusuariosById " +
            "where rolusuariosById.usuarioByIdusuario = :user and rolusuariosById.empresaByIdempresa.cif = :cif")
    List<RolEntity> findRolesByUsuarioAndEmpresaByCif(@Param("user") UsuarioEntity user, @Param("cif") Integer cif);

    @Query("select rolusuariosById.rolByIdrol from RolusuarioEntity rolusuariosById " +
            "where rolusuariosById.usuarioByIdusuario = :user and rolusuariosById.empresaByIdempresa is null")
    List<RolEntity> findRolesByUsuarioNoEmpresa(@Param("user") UsuarioEntity user);
}
