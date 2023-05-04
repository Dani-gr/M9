package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionEntityRepository extends JpaRepository<OperacionEntity, Integer> {
}