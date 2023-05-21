package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.EmpresaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.EmpresaEntityDTO;
import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    EmpresaEntityRepository empresaEntityRepository;

    public EmpresaEntityDTO buscaCif(Integer cif) {
        EmpresaEntity empresa = empresaEntityRepository.findByCif(cif).orElse(null);
        return empresa == null ? null : empresa.toDTO();
    }

    public void guardar(EmpresaEntityDTO empresa) {
        empresaEntityRepository.save(empresa.toEntity());
    }

    public void vaciarBBDD() {
        empresaEntityRepository.deleteAll();
    }
}
