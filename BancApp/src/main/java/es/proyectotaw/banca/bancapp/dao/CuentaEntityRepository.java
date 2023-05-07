package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public interface CuentaEntityRepository extends JpaRepository<CuentaEntity, Integer> {

    /*Estados de una cuenta
        Activa = 0 -> Bloqueada
        Activa = 1 -> Activa
        Activa = 2 -> Bloqueada pendiente de Activar

     */
    @Query("select c from CuentaEntity c where c.activa = 2")
    List<CuentaEntity> getCuentasPendientesDeActivar();

    @Query("SELECT c FROM CuentaEntity c")
    List<CuentaEntity> findAllCuentasConUltimaOperacionHace30Dias();

    default boolean esUltimaOperacionHace30Dias(List<OperacionEntity> operaciones) {
        // Ordenar la lista de operaciones por fecha en orden descendente
        operaciones.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));

        // Obtener la fecha de la primera operación en la lista
        LocalDate fechaUltimaOperacion = operaciones.get(0).getFecha().toLocalDate();

        // Verificar si la fecha de la última operación es mayor a la fecha actual menos 30 días
        return fechaUltimaOperacion.isBefore(LocalDate.now().minusDays(30));
    }

    default List<CuentaEntity> getCuentasConUltimaOperacionHace30Dias() {
        List<CuentaEntity> cuentas = findAll();
        List<CuentaEntity> cuentasConUltimaOperacionHace30Dias = new ArrayList<>();

        for (CuentaEntity cuenta : cuentas) {
            if (esUltimaOperacionHace30Dias(cuenta.getOperacionsByNumCuenta())) {
                cuentasConUltimaOperacionHace30Dias.add(cuenta);
            }
        }

        return cuentasConUltimaOperacionHace30Dias;
    }
}
