package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select c from UsuarioEntity c where c.id = :id")
    UsuarioEntity buscarPorID(@Param("id") Integer id);
}
