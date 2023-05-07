package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public interface CuentaEntityRepository extends JpaRepository<CuentaEntity, Integer> {

    /*@Query("SELECT c FROM CuentaEntity c WHERE (SELECT MAX(o.fecha) FROM c.operacionesByIdCuenta o) <= :fechaMaxima")
    default List<CuentaEntity> encuentraCuentasSinActividadEnDias(@Param("dias") Integer dias) {
        java.sql.Date fechaMaxima = java.sql.Date.valueOf(LocalDate.now().minusDays(dias));
        return encontrarCuentasSinActividadEnDias(fechaMaxima);
    }

    @Query("SELECT c FROM CuentaEntity c WHERE (SELECT MAX(o.fecha) FROM c.operacionesByIdCuenta o) <= :fechaMaxima")
    List<CuentaEntity> encontrarCuentasSinActividadEnDias(@Param("fechaMaxima") java.sql.Date fechaMaxima);*/

}
