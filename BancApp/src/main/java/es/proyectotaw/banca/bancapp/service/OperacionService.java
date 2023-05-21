package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.OperacionEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CuentaEntityDTO;
import es.proyectotaw.banca.bancapp.dto.ExtraccionEntityDTO;
import es.proyectotaw.banca.bancapp.dto.OperacionEntityDTO;
import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {
    @Autowired
    OperacionEntityRepository operacionEntityRepository;

    public List<OperacionEntityDTO> ordenarPorCantidad(CuentaEntityDTO cuentaEntityDTO){
        List<OperacionEntity> operaciones = operacionEntityRepository.getOperacionesOrdenadasPorCantidad(cuentaEntityDTO.toEntity());
        List<OperacionEntityDTO> operacionesDTO = new ArrayList<>();
        for(OperacionEntity ope : operaciones) {
            operacionesDTO.add(ope.toDTO());
        }
        return operacionesDTO.isEmpty() ? null : operacionesDTO;
    }

    public void guardar(OperacionEntityDTO... operaciones) {
        for (OperacionEntityDTO operacion : operaciones) operacionEntityRepository.save(operacion.toEntity());
    }

    public void vaciarBBDD() {
        operacionEntityRepository.deleteAll();
    }
}
