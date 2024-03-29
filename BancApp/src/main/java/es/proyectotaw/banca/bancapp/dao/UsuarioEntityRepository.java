package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByRolusuariosById_RolByIdrol_Nombre(String nombre);

    @Query("select u from UsuarioEntity u where upper(u.email) = upper(?1)")
    Optional<UsuarioEntity> findByEmailIgnoreCase(@NonNull String email);

    @Query("select c from UsuarioEntity c where c.id = :id")
    UsuarioEntity buscarPorID(@Param("id") Integer id);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :id")
    List<UsuarioEntity> findUsuariosByEmpresa(@Param("id") Integer id);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :id " +
            "ORDER BY u.primerApellido ASC")
    List<UsuarioEntity> findUsuariosByEmpresaOrderByApellido(@Param("id") Integer id);


    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :id and ru.bloqueado = 1")
    List<UsuarioEntity> findUsuariosBloqueadosByEmpresa(@Param("id") Integer id);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa and ru.rolByIdrol = :rol")
    List<UsuarioEntity> findUsuariosByEmpresaAndRol(@Param("idEmpresa") Integer idEmpresa, @Param("rol") RolEntity rol);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa " +
            "AND ru.rolByIdrol = :rol ORDER BY u.primerApellido ASC")
    List<UsuarioEntity> findUsuariosByEmpresaAndRolOrderByApellido(@Param("idEmpresa") Integer idEmpresa, @Param("rol") RolEntity rol);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa " +
            "AND ru.rolByIdrol = :rol " +
            "AND (u.primerNombre LIKE %:cadena% OR u.segundoNombre LIKE %:cadena% OR u.primerApellido LIKE %:cadena% OR u.segundoApellido LIKE %:cadena%)")
    List<UsuarioEntity> findUsuariosByEmpresaAndRolAndCadena(@Param("idEmpresa") Integer idEmpresa, @Param("rol") RolEntity rol, @Param("cadena") String cadena);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa " +
            "AND (u.primerNombre LIKE %:cadena% OR u.segundoNombre LIKE %:cadena% OR u.primerApellido LIKE %:cadena% OR u.segundoApellido LIKE %:cadena%)")
    List<UsuarioEntity> findUsuariosByEmpresaAndCadena(@Param("idEmpresa") Integer idEmpresa, @Param("cadena") String cadena);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa " +
            "AND (u.primerNombre LIKE %:cadena% OR u.segundoNombre LIKE %:cadena% OR u.primerApellido LIKE %:cadena% OR u.segundoApellido LIKE %:cadena%) " +
            "ORDER BY u.primerApellido ASC")
    List<UsuarioEntity> findUsuariosByEmpresaAndCadenaOrderByApellido(@Param("idEmpresa") Integer idEmpresa, @Param("cadena") String cadena);


    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.idEmpresa = :idEmpresa " +
            "AND ru.rolByIdrol = :rol " +
            "AND (u.primerNombre LIKE %:cadena% OR u.segundoNombre LIKE %:cadena% OR u.primerApellido LIKE %:cadena% OR u.segundoApellido LIKE %:cadena%) " +
            "ORDER BY u.primerApellido ASC")
    List<UsuarioEntity> findUsuariosByEmpresaAndRolAndCadenaOrderByApellido(@Param("idEmpresa") Integer idEmpresa, @Param("rol") RolEntity rol, @Param("cadena") String cadena);


}