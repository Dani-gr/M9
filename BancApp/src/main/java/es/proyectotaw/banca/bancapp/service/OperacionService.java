package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.OperacionEntityRepository;
import es.proyectotaw.banca.bancapp.dto.OperacionEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacionService {
    @Autowired
    OperacionEntityRepository operacionEntityRepository;

    public void guardar(OperacionEntityDTO operacion) {
        operacionEntityRepository.save(operacion.toEntity());
    }

}
