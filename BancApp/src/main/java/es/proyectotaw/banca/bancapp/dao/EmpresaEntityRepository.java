package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import es.proyectotaw.banca.bancapp.entity.RolusuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface EmpresaEntityRepository extends JpaRepository<EmpresaEntity, Integer> {
    @Query("select e from EmpresaEntity e where UPPER(e.cif) = UPPER(?1)")
    Optional<EmpresaEntity> findByCif(@NonNull Integer cif);

    @Query("select a.rolusuariosById from EmpresaEntity a where a.id = :id")
    public List<RolusuarioEntity> findAllRoles(@RequestParam("id") Integer id);
}
