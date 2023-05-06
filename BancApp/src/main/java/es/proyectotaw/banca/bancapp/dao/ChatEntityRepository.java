package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
    List<ChatEntity> findByClienteByClienteIdCliente_UsuariosByIdCliente_PrimerNombre(String primerNombre);

    //List<ChatEntity> findByClienteByClienteIdCliente();

    //List<ChatEntity> findByClienteByClienteIdCliente_IdCliente(Integer idCliente);
}