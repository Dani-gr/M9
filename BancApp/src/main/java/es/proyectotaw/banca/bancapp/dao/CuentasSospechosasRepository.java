package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentasSospechosasRepository extends JpaRepository<CuentasSospechosasEntity, Integer> {

    @Query("select c.iban from CuentasSospechosasEntity c ")
    List<String> obtenerIbans();
}
