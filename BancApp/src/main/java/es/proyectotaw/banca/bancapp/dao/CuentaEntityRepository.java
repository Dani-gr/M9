package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CuentaEntityRepository extends JpaRepository<CuentaEntity, Integer> {

    @Query("select c.operacionsByNumCuenta from CuentaEntity c where c.numCuenta =:id")
    List<OperacionEntity> buscarTodasLasOperaciones(@RequestParam("id") Integer id);
}
