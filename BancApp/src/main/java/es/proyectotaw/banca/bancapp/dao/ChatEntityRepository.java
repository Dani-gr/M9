package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
    List<ChatEntity> findByClienteByClienteIdCliente_IdCliente(Integer idCliente);

    List<ChatEntity> findByClienteByClienteIdCliente_UsuariosByIdCliente_PrimerNombre(String primerNombre);

    @Override
    Optional<ChatEntity> findById(Integer integer);

    //List<ChatEntity> findByClienteByClienteIdCliente();

    //List<ChatEntity> findByClienteByClienteIdCliente_IdCliente(Integer idCliente);
}