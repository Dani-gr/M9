package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where upper(u.email) = upper(?1)")
    Optional<UsuarioEntity> findByEmailIgnoreCase(@NonNull String email);

    @Query("select c from UsuarioEntity c where c.id = :id")
    UsuarioEntity buscarPorID(@Param("id") Integer id);

    @Query("SELECT u FROM UsuarioEntity u JOIN u.rolusuariosById ru WHERE ru.empresaByIdempresa.id = :id")
    Optional<UsuarioEntity> findUsuariosByEmpresa(@Param("id") Integer id);
}