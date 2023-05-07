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

    /*Estados de una cuenta
        Activa = 0 -> Bloqueada
        Activa = 1 -> Activa
        Activa = 2 -> Bloqueada pendiente de Activar

     */
    @Query("select c from CuentaEntity c where c.activa = 2")
    List<CuentaEntity> getCuentasPendientesDeActivar();

}
