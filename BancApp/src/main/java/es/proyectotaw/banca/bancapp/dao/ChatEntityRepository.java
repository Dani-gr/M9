package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
}
