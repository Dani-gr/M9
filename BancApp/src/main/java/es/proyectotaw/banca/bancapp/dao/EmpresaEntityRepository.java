package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface EmpresaEntityRepository extends JpaRepository<EmpresaEntity, Integer> {
    @Query("select e from EmpresaEntity e where UPPER(e.cif) = UPPER(?1)")
    Optional<EmpresaEntity> findByCif(@NonNull Integer cif);

}
