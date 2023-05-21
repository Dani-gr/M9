package es.proyectotaw.banca.bancapp.service;

import es.proyectotaw.banca.bancapp.dao.CuentaEntityRepository;
import es.proyectotaw.banca.bancapp.dto.CuentaEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    @Autowired
    CuentaEntityRepository cuentaEntityRepository;

    public CuentaEntityDTO buscaPorIBAN(Integer iban) {
        var cuenta = cuentaEntityRepository.findById(iban).orElse(null);
        return cuenta == null ? null : cuenta.toDTO();
    }

    public void guardar(CuentaEntityDTO... cuentas) {
        for (CuentaEntityDTO cuenta : cuentas) cuentaEntityRepository.save(cuenta.toEntity());
    }

    public void vaciarBBDD() {
        cuentaEntityRepository.deleteAll();
    }
}
