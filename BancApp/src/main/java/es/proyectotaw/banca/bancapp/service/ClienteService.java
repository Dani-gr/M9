package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dto.ClienteEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteEntityRepository clienteEntityRepository;

    public void guardar(ClienteEntityDTO... clientes) {
        for (ClienteEntityDTO cliente : clientes) clienteEntityRepository.save(cliente.toEntity());
    }

    public void vaciarBBDD() {
        clienteEntityRepository.deleteAll();
    }
}
