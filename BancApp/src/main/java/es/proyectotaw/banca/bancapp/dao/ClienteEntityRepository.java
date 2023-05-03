package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer> {
    @Query("select c from ClienteEntity c where c.idCliente = :id")
    public ClienteEntity buscarPorID(@Param("id") Integer id);
}
