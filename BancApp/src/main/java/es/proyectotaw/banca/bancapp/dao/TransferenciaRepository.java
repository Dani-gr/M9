package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;
import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity,Integer> {

    @Query("select t from TransferenciaEntity t where t.cantidad >= :cantidad")
    List<TransferenciaEntity> filtrarPorCantidad(@RequestParam("cantidad") float cantidad);
}