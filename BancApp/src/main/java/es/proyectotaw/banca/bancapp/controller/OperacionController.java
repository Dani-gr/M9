package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.TransferenciaRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    protected TransferenciaRepository transferenciaRepository;

    @GetMapping("/")
    public String doAlmacenarOperacion(Model model){
        model.addAttribute("fecha", new Date());

        return "menu";
    }

    @GetMapping("/transferencia")
    public String doTransferencia(Model model, @RequestParam("cuentas") List<CuentaEntity> cuentas) {
        model.addAttribute("cuentas", cuentas);

        model.addAttribute("transferenciaARealizar", new TransferenciaEntity());

        return "transferencia";
    }

    @PostMapping("/guardar")
    public String doGuardarTransferencia(@ModelAttribute("transferenciaARealizar") TransferenciaEntity transferencia) {
        transferenciaRepository.save(transferencia);
        return "menu";
    }
}
