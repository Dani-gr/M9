/*
Autor: Andres Perez Garcia
 */
package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
    List<ChatEntity> findAllByActivo(Byte activo);
    @Transactional
    @Modifying
    @Query("update ChatEntity c set c.activo = ?1 where c.id = ?2")
    void updateActivoById(Byte activo, Integer id);
    List<ChatEntity> findByClienteByClienteIdCliente_IdCliente(Integer idCliente);

    List<ChatEntity> findByClienteByClienteIdCliente_UsuariosByIdCliente_PrimerNombre(String primerNombre);

    @Override
    Optional<ChatEntity> findById(Integer integer);

}