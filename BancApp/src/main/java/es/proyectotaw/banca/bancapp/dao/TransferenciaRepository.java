package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity,Integer> {
}
