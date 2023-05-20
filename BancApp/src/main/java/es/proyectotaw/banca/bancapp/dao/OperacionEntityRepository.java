package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OperacionEntityRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("SELECT o FROM OperacionEntity o "
            + "JOIN o.cuentaByCuentaRealiza c "
            + "WHERE c.numCuenta = :numCuenta")
    List<OperacionEntity> getOperacionesByNumeroCuenta(@Param("numCuenta") Integer numCuenta);

    @Query("SELECT o FROM OperacionEntity o "
            + "LEFT JOIN o.cambDivisasByIdOperacion cd "
            + "LEFT JOIN o.extraccionsByIdOperacion ex "
            + "LEFT JOIN o.transferenciasByIdOperacion tr "
            + "JOIN o.cuentaByCuentaRealiza c "
            + "WHERE c.numCuenta = :numCuenta "
            + "ORDER BY COALESCE(cd.cantidad, ex.cantidad, tr.cantidad) ASC") // Cambia ASC a DESC si quieres orden descendente
    List<OperacionEntity> getOperacionesByNumeroCuentaOrderByCantidad(@Param("numCuenta") Integer numCuenta);


    @Query("SELECT o FROM OperacionEntity o "
            + "JOIN o.cuentaByCuentaRealiza c "
            + "WHERE c.numCuenta = :numCuenta "
            + "AND o.fecha = :fechaOperacion")
    List<OperacionEntity> getOperacionesByNumeroCuentaAndFecha(@Param("numCuenta") Integer numCuenta, @Param("fechaOperacion") Date fechaOperacion);


public interface OperacionEntityRepository extends JpaRepository<OperacionEntity, Integer> {

    @Query("SELECT o FROM OperacionEntity o LEFT JOIN o.cambDivisasByIdOperacion cd LEFT JOIN o.transferenciasByIdOperacion t LEFT JOIN o.extraccionsByIdOperacion e WHERE o.cuentaByCuentaRealiza = :cuenta " +
            "ORDER BY CASE " +
            "WHEN cd IS NOT NULL THEN cd.cantidad " +
            "WHEN t IS NOT NULL THEN t.cantidad " +
            "WHEN e IS NOT NULL THEN e.cantidad " +
            "END ASC")
    List<OperacionEntity> getOperacionesOrdenadasPorCantidad(@Param("cuenta") CuentaEntity cuenta);


}