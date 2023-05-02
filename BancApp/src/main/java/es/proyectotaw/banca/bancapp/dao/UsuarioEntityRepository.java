package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where upper(u.email) = upper(?1)")
    Optional<UsuarioEntity> findByEmailIgnoreCase(@NonNull String email);
}