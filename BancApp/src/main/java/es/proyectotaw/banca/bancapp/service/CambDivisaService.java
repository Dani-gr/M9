package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.CambDivisaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CambDivisaEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CambDivisaService {
    @Autowired
    CambDivisaEntityRepository cambDivisaEntityRepository;

    public void guardar(CambDivisaEntityDTO cambioDivisa) {
        cambDivisaEntityRepository.save(cambioDivisa.toEntity());
    }
}
