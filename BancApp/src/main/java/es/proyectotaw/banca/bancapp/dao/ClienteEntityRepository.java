package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) = 0")
    List<ClienteEntity> obtenerAspirantesCliente();

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0")
    List<ClienteEntity> obtenerClientesDadosDeAlta();


    @Query("SELECT DISTINCT c FROM ClienteEntity c JOIN c.cuentasByIdCliente cc WHERE cc.saldo >= :limiteInferior")
    List<ClienteEntity> obtenerCLientesPorSaldoMinimo(@Param("limiteInferior") Double limiteInferior);


    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and SIZE(c.cuentasByIdCliente) > 0 and " +
                                            "UPPER(c.direccionByDireccion.ciudad) like CONCAT('%', UPPER(:ciudad), '%')  ")
    List<ClienteEntity> obtenerClientesPorCiudad(@Param("ciudad") String ciudad);

    @Query("SELECT c FROM ClienteEntity c WHERE SIZE(c.cuentasByIdCliente) > 0 and c.idCliente IN " +
            "(SELECT cc.clienteByCliente.idCliente FROM CuentaEntity cc WHERE SIZE(c.cuentasByIdCliente) > 0 and cc.saldo >= :limiteInferior) AND " +
            "UPPER(c.direccionByDireccion.ciudad) LIKE CONCAT('%', UPPER(:ciudad), '%')")
    List<ClienteEntity> obtenerCLientesPorSaldoMinimoYCiudad(@Param("limiteInferior") Double limiteInferior,
                                                             @Param("ciudad") String ciudad);


}
