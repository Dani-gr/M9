package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaEntityRepository extends JpaRepository<CuentaEntity, Integer> {
}
