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

    /*Filtro de clientes*/

    /*@Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
                                            "c.cuentasByIdCliente[0].saldo >= :lim")
    List<ClienteEntity> obtenerClientesPorSaldoMinimo(@Param("lim") Float lim);

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
                                            "c.direccionByDireccion.ciudad like CONCAT('%', :ciudad, '%')  ")
    List<ClienteEntity> obtenerClientesPorCiudad(@Param("ciudad") String ciudad);

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
                                            "c.cuentasByIdCliente[0].activa = :estado")
    List<ClienteEntity> obtenerClientesPorEstadoDeCuenta(@Param("estado") Boolean estado);

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
            "c.cuentasByIdCliente[0].saldo >= :lim and c.direccionByDireccion.ciudad like CONCAT('%', :ciudad, '%')  ")
    List<ClienteEntity> obtenerCLientesPorSaldoMinimoYCiudad(@Param("lim") Float lim, @Param("ciudad") String ciudad);

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
            "c.cuentasByIdCliente[0].saldo >= :lim")
    List<ClienteEntity> obtenerCLientesPorSaldoMinimoYEstadoDeCuenta(@Param("lim") Float lim, @Param("estado") Boolean estado);

    @Query()
    List<ClienteEntity> obtenerCLientesPorCiudadYEstadoDeCuenta();

    @Query()
    List<ClienteEntity> obtenerCLientesPorCiudadSaldoMinimoYEstadoDeCuenta();*/

}
