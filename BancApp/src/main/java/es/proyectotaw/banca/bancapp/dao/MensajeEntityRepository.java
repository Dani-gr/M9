package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeEntityRepository extends JpaRepository<MensajeEntity, Integer> {
}