package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionEntityRepository extends JpaRepository<OperacionEntity, Integer> {

    @Query("SELECT o FROM OperacionEntity o LEFT JOIN o.cambDivisasByIdOperacion cd LEFT JOIN o.transferenciasByIdOperacion t LEFT JOIN o.extraccionsByIdOperacion e WHERE o.cuentaByCuentaRealiza = :cuenta " +
            "ORDER BY CASE " +
            "WHEN cd IS NOT NULL THEN cd.cantidad " +
            "WHEN t IS NOT NULL THEN t.cantidad " +
            "WHEN e IS NOT NULL THEN e.cantidad " +
            "END ASC")
    List<OperacionEntity> getOperacionesOrdenadasPorCantidad(@Param("cuenta") CuentaEntity cuenta);

}