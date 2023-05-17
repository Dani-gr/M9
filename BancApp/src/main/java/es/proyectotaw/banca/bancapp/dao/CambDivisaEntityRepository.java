package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CambDivisaEntityRepository extends JpaRepository<CambDivisaEntity, Integer> {

    @Query("select c from CambDivisaEntity c where c.cantidad >= :cantidad")
    List<CambDivisaEntity> filtrarPorCantidad(@RequestParam("cantidad") float cantidad);
}