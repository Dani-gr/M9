package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.MensajeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeEntityRepository extends JpaRepository<MensajeEntity, Integer> {
    List<MensajeEntity> findByChatByChat_Id(Integer id, Sort sortByFechaDesc);
}