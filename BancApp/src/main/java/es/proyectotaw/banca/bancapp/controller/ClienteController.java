package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.CuentaEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.entity.OperacionEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;

    @Autowired
    protected UsuarioEntityRepository usuarioEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @GetMapping("/")
    public String doPasarAlMenu(Model model){
        //TODO se pasa al menú solo y solo si tenemos el email y la contraseña
        String urlTo="menu";

        return urlTo;
    }

    @GetMapping("/perfil")
    public String doVerPerfil(Model model) {
        //@RequestParam("idUsuario") Integer id
        //UsuarioEntity usuario = usuarioRepository.buscarPorID(id);
        UsuarioEntity usuario = new UsuarioEntity();
        ClienteEntity cliente = new ClienteEntity();
        List<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
        CuentaEntity cuenta = cuentas.get(0);
        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", new ClienteEntity());
        List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();
        //todo
        return "cliente";
    }
}
