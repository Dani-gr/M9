package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ExtraccionEntityRepository extends JpaRepository<ExtraccionEntity, Integer> {

    @Query("select e from ExtraccionEntity e where e.cantidad >= :cantidad")
    List<ExtraccionEntity> filtrarPorCantidad(@RequestParam("cantidad") float cantidad);
}
