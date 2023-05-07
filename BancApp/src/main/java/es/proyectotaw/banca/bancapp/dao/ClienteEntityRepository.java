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


    /*@Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
                                            " c.")
    List<ClienteEntity> obtenerClientesPorSaldoMinimo(@Param("lim") Double lim);*/

    @Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
                                            "UPPER(c.direccionByDireccion.ciudad) like CONCAT('%', UPPER(:ciudad), '%')  ")
    List<ClienteEntity> obtenerClientesPorCiudad(@Param("ciudad") String ciudad);

    /*@Query("select c from ClienteEntity c where SIZE(c.cuentasByIdCliente) > 0 and " +
            "c.cuentasByIdCliente[0].saldo >= :lim and c.direccionByDireccion.ciudad like CONCAT('%', :ciudad, '%')  ")
    List<ClienteEntity> obtenerCLientesPorSaldoMinimoYCiudad(@Param("lim") Double lim, @Param("ciudad") String ciudad);
    */
}
