package es.proyectotaw.banca.bancapp.controller;

import com.sun.xml.bind.v2.TODO;
import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.ui.FiltroOperaciones;
import es.proyectotaw.banca.bancapp.ui.OrdenarOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    protected TransferenciaRepository transferenciaRepository;

    @Autowired
    protected OperacionEntityRepository operacionEntityRepository;

    @Autowired
    protected CambDivisaEntityRepository cambDivisaEntityRepository;

    @Autowired
    protected ExtraccionEntityRepository extraccionEntityRepository;


    @GetMapping("/")
    public String doPasarAlMenu(Model model, HttpSession session){
        String urlTo;

        if(session.getAttribute("usuario") == null && session.getAttribute("empresa") == null) {
            urlTo="login";
        } else {
            urlTo="menu";
        }

        return urlTo;
    }

    @GetMapping("/perfil")
    public String doVerPerfil(Model model, HttpSession session, @ModelAttribute("entidad") String entidad) {
        //@RequestParam("idUsuario") Integer id
        //UsuarioEntity usuario = usuarioRepository.buscarPorID(id);
        /*model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        if("empresa".equals(entidad)) {

        } else {
            UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
            ClienteEntity cliente = usuario.getClienteByCliente();
            List<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
            CuentaEntity cuenta = cuentas.get(0);
            model.addAttribute("usuario", usuario);
            model.addAttribute("cliente", cliente);
            List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("operaciones", operaciones);
        }*/
        /*
        * esto porque si no me da error pa probar VALE?*/
        CuentaEntity cuenta = cuentaEntityRepository.getById(6);
        model.addAttribute("usuario", new UsuarioEntity());
        model.addAttribute("cliente", new ClienteEntity());
        List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("operaciones", operaciones);
        return "cliente";
    }

    @PostMapping("/filtrar")
    public String doFiltrar (@ModelAttribute("filtro") FiltroOperaciones filtro,
                             Model model, HttpSession session) {
        return this.procesarFiltrado(filtro, model, session);
    }

    @GetMapping("/filtrar")
    protected String procesarFiltrado (FiltroOperaciones filtro,
                                       Model model, HttpSession session) {
        List<OperacionEntity> operaciones = new ArrayList<>();
        String urlTo = "cliente";
        //lo de la session
        if (filtro == null || (filtro.getCantidadFiltro() == 0 && filtro.getNombreOperacion().equals("ninguno"))) {
            filtro = new FiltroOperaciones();
            operaciones = operacionEntityRepository.findAll();
        } else {
            if(!filtro.getNombreOperacion().equals("ninguno")) {
                String nombre = filtro.getNombreOperacion();
                if(filtro.getCantidadFiltro() == 0) {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntity> transferencias = transferenciaRepository.findAll();
                        for (TransferenciaEntity trans : transferencias) {
                            operaciones.add(trans.getOperacionByOperacion());
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntity> cambios = cambDivisaEntityRepository.findAll();
                            for (CambDivisaEntity cambio : cambios) {
                                operaciones.add(cambio.getOperacionByOperacion());
                            }
                        } else {
                            List<ExtraccionEntity> extracciones = extraccionEntityRepository.findAll();
                            for (ExtraccionEntity extraccion : extracciones) {
                                operaciones.add(extraccion.getOperacionByOperacion());
                            }
                        }
                    }
                } else {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntity> transferencias = transferenciaRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                        for (TransferenciaEntity trans : transferencias) {
                            operaciones.add(trans.getOperacionByOperacion());
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntity> cambios = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (CambDivisaEntity cambio : cambios) {
                                operaciones.add(cambio.getOperacionByOperacion());
                            }
                        } else {
                            List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (ExtraccionEntity extraccion : extracciones) {
                                operaciones.add(extraccion.getOperacionByOperacion());
                            }
                        }
                    }
                }
            } else {
                if(filtro.getCantidadFiltro() != 0) {
                    List<TransferenciaEntity> transferencias = transferenciaRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (TransferenciaEntity trans : transferencias) {
                        operaciones.add(trans.getOperacionByOperacion());
                    }
                    List<CambDivisaEntity> cambios = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (CambDivisaEntity cambio : cambios) {
                        operaciones.add(cambio.getOperacionByOperacion());
                    }
                    List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (ExtraccionEntity extraccion : extracciones) {
                        operaciones.add(extraccion.getOperacionByOperacion());
                    }
                }
            }
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("operaciones", operaciones);

        return urlTo;
    }

    @PostMapping("/ordenar")
    public String doOrdenar (@ModelAttribute("ordenar") OrdenarOperaciones orden,
                             Model model, HttpSession session) {
        return this.procesarOrdenado(orden, model, session);
    }

    protected String procesarOrdenado (OrdenarOperaciones orden,
                                       Model model, HttpSession session) {

        model.addAttribute("ordenar", orden);

        return "cliente";
    }

    @PostMapping("/guardar")
    public String doGuardarPerfil(HttpSession session, @ModelAttribute("usuario") UsuarioEntity usur) {
        //si es particular
        usuarioEntityRepository.save(usur);
        return "redirect:/customer/";
    }
}