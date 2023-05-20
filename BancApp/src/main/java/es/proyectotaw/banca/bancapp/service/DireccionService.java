package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.DireccionEntityRepository;
import es.proyectotaw.banca.bancapp.dto.DireccionEntityDTO;
import es.proyectotaw.banca.bancapp.entity.DireccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    @Autowired
    DireccionEntityRepository direccionEntityRepository;

    public DireccionEntityDTO creaDireccion(String calle, Integer numero, String plantaPuertaOficina, String ciudad, String region, String pais, String codpostal) {
        DireccionEntity tmp = new DireccionEntity();
        tmp.construct(calle, numero, plantaPuertaOficina, ciudad, region, pais, codpostal);
        return tmp.toDTO();
    }

    public void guardar(DireccionEntityDTO... direcciones) {
        for (DireccionEntityDTO direccion : direcciones) direccionEntityRepository.save(direccion.toEntity());
    }

    public void vaciarBBDD() {
        direccionEntityRepository.deleteAll();
    }
}
