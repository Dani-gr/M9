package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    @Query("select r from RolEntity r where r.nombre = :nombre")
    public RolEntity buscarPorID(@Param("nombre") String nombre);
}
