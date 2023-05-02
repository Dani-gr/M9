package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface RolEntityRepository extends JpaRepository<RolEntity, Integer> {
    @Query("select r from RolEntity r where upper(r.nombre) = upper(?1)")
    Optional<RolEntity> findByNombre(@NonNull String nombre);
}
