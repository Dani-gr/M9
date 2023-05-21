package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.ExtraccionEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CambDivisaEntityDTO;
import es.proyectotaw.banca.bancapp.dto.ExtraccionEntityDTO;
import es.proyectotaw.banca.bancapp.dto.OperacionEntityDTO;
import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;
import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtraccionService {
    @Autowired
    ExtraccionEntityRepository extraccionEntityRepository;

    public ExtraccionEntityDTO encontrarPorOperacion(OperacionEntityDTO ope) {
        ExtraccionEntity extra = extraccionEntityRepository.findByOperation(ope.getIdOperacion());
        return  extra == null ? null : extra.toDTO();
    }

    public List<ExtraccionEntityDTO> filtrarPorCantidad(Double cantidad) {
        List<ExtraccionEntity> extras = extraccionEntityRepository.filtrarPorCantidad(cantidad);
        List<ExtraccionEntityDTO> extrasdto = new ArrayList<>();
        for(ExtraccionEntity extra : extras) {
            extrasdto.add(extra.toDTO());
        }
        return extrasdto.isEmpty() ? null : extrasdto;
    }


    public void guardar(ExtraccionEntityDTO extra) {
        extraccionEntityRepository.save(extra.toEntity());
    }

    public void vaciarBBDD() {
        extraccionEntityRepository.deleteAll();
    }
}
