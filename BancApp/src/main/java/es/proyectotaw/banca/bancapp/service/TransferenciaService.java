package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.TransferenciaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.TransferenciaEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaEntityRepository transferenciaEntityRepository;

    public void guardar(TransferenciaEntityDTO transferencia) {
        transferenciaEntityRepository.save(transferencia.toEntity());
    }

    public void vaciarBBDD() {
        transferenciaEntityRepository.deleteAll();
    }
}
