package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.CambDivisaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CambDivisaEntityDTO;
import es.proyectotaw.banca.bancapp.dto.OperacionEntityDTO;
import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CambDivisaService {
    @Autowired
    CambDivisaEntityRepository cambDivisaEntityRepository;

    public CambDivisaEntityDTO encontrarPorOperacion(OperacionEntityDTO ope) {
        CambDivisaEntity cambio = cambDivisaEntityRepository.findByOperation(ope.getIdOperacion());
        return  cambio == null ? null : cambio.toDTO();
    }

    public List<CambDivisaEntityDTO> filtrarPorCantidad(Double cantidad) {
        List<CambDivisaEntity> cambios = cambDivisaEntityRepository.filtrarPorCantidad(cantidad);
        List<CambDivisaEntityDTO> cambiosdto = new ArrayList<>();
        for(CambDivisaEntity cambio : cambios) {
            cambiosdto.add(cambio.toDTO());
        }
        return cambiosdto.isEmpty() ? null : cambiosdto;
    }

    public void guardar(CambDivisaEntityDTO... cambiosDivisas) {
        for (CambDivisaEntityDTO cd : cambiosDivisas) cambDivisaEntityRepository.save(cd.toEntity());
    }

    public void vaciarBBDD() {
        cambDivisaEntityRepository.deleteAll();
    }
}
