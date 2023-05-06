package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
}