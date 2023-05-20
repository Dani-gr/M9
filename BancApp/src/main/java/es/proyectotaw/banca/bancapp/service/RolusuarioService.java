package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.RolusuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dto.RolusuarioEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolusuarioService {
    @Autowired
    RolusuarioEntityRepository rolusuarioEntityRepository;

    public void guardar(RolusuarioEntityDTO... rolusuarios) {
        for (RolusuarioEntityDTO ru : rolusuarios) rolusuarioEntityRepository.save(ru.toEntity());
    }

    public void vaciarBBDD() {
        rolusuarioEntityRepository.deleteAll();
    }
}
