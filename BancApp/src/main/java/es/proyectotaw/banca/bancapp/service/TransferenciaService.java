package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.TransferenciaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CambDivisaEntityDTO;
import es.proyectotaw.banca.bancapp.dto.ExtraccionEntityDTO;
import es.proyectotaw.banca.bancapp.dto.OperacionEntityDTO;
import es.proyectotaw.banca.bancapp.dto.TransferenciaEntityDTO;
import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;
import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;
import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaEntityRepository transferenciaEntityRepository;

    public TransferenciaEntityDTO encontrarPorOperacion(OperacionEntityDTO ope) {
        TransferenciaEntity trans = transferenciaEntityRepository.findByOperation(ope.getIdOperacion());
        return trans == null ? null : trans.toDTO();
    }

    public List<TransferenciaEntityDTO> filtrarPorCantidad(Double cantidad) {
        List<TransferenciaEntity> transs = transferenciaEntityRepository.filtrarPorCantidad(cantidad);
        List<TransferenciaEntityDTO> transsdto = new ArrayList<>();
        for(TransferenciaEntity trans : transs) {
            transsdto.add(trans.toDTO());
        }
        return transsdto.isEmpty() ? null : transsdto;
    }


    public void guardar(TransferenciaEntityDTO transferencia) {
        transferenciaEntityRepository.save(transferencia.toEntity());
    }

    public void vaciarBBDD() {
        transferenciaEntityRepository.deleteAll();
    }
}
