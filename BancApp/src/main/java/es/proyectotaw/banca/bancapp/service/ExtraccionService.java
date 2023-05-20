package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.ExtraccionEntityRepository;
import es.proyectotaw.banca.bancapp.dto.ExtraccionEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraccionService {
    @Autowired
    ExtraccionEntityRepository extraccionEntityRepository;

    public void guardar(ExtraccionEntityDTO extra) {
        extraccionEntityRepository.save(extra.toEntity());
    }

    public void vaciarBBDD() {
        extraccionEntityRepository.deleteAll();
    }
}
