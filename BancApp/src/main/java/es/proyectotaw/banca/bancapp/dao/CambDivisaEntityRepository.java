package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;
import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CambDivisaEntityRepository extends JpaRepository<CambDivisaEntity, Integer> {

    @Query("select c from CambDivisaEntity c where c.cantidad >= :cantidad")
    List<CambDivisaEntity> filtrarPorCantidad(@RequestParam("cantidad") Double cantidad);

    @Query("select c from CambDivisaEntity c where c.operacionByOperacion.idOperacion = :id")
    CambDivisaEntity findByOperation(@RequestParam("id") int id);
}