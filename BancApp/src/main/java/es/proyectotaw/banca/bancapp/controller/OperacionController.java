package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
@Controller
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    protected TransferenciaEntityRepository transferenciaEntityRepository;

    @Autowired
    protected OperacionEntityRepository operacionEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @Autowired
    protected CambDivisaEntityRepository cambDivisaEntityRepository;

    @Autowired
    protected ExtraccionEntityRepository extraccionEntityRepository;

    @GetMapping("/")
    public String doAlmacenarOperacion(Model model) {
        model.addAttribute("fecha", new Date(System.currentTimeMillis()));

        return "menu";
    }

    @GetMapping("/transferencia")
    public String doTransferencia(Model model, HttpSession session) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        ClienteEntity cliente = usuario.getClienteByCliente();
        List<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
        CuentaEntity cuenta = cuentas.get(0);

        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByCuentaRealiza(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));
        operacionEntityRepository.save(operacion);
        TransferenciaEntity transferencia = new TransferenciaEntity();
        transferencia.setOperacionByOperacion(operacion);
        transferencia.setCantidad((double) 0);
        transferenciaEntityRepository.save(transferencia);
        List<TransferenciaEntity> trans = new ArrayList<>();
        trans.add(transferencia);
        operacion.setTransferenciasByIdOperacion(trans);
        operacionEntityRepository.save(operacion);
        model.addAttribute("transferenciaARealizar", transferencia);

        return "transferencia";
    }

    @PostMapping("/guardarTransferencia")
    public String doGuardarTransferencia(@ModelAttribute("transferenciaARealizar") TransferenciaEntity transferencia) {
        transferenciaEntityRepository.save(transferencia);
        OperacionEntity operacion = transferencia.getOperacionByOperacion();
        List<TransferenciaEntity> trans = new ArrayList<>();
        trans.add(transferencia);
        operacion.setTransferenciasByIdOperacion(trans);
        operacionEntityRepository.save(operacion);

        if (transferencia.getIbanDestino() == null) {
            CuentaEntity mia = operacion.getCuentaByCuentaRealiza();
            CuentaEntity otro = transferencia.getCuentaByCuentaDestino();
            Double cantidad = transferencia.getCantidad();

            if (mia.getSaldo() - cantidad >= 0) {
                mia.setSaldo(mia.getSaldo() - cantidad);
                otro.setSaldo(otro.getSaldo() + cantidad);
                //que diga algo "Felicidades, ahora estás más probre"
                //No sé si es necesario, por si acaso
                cuentaEntityRepository.save(mia);
                cuentaEntityRepository.save(otro);
            } else {
                //TODO estas pobre
            }
        }
        return "menu";
    }

    @GetMapping("/cambioDivisa")
    public String doDivisa(Model model, HttpSession session) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        ClienteEntity cliente = usuario.getClienteByCliente();
        List<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
        CuentaEntity cuenta = cuentas.get(0);

        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByCuentaRealiza(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));
        operacionEntityRepository.save(operacion);
        CambDivisaEntity cambio = new CambDivisaEntity();
        cambio.setOperacionByOperacion(operacion);
        model.addAttribute("cambioDivisa", cambio);

        return "cambiodivisa";
    }

    @PostMapping("/guardarDivisa")
    public String doGuardarDivisa(@ModelAttribute("cambioDivisa") CambDivisaEntity cambioDivisa) {
        cambDivisaEntityRepository.save(cambioDivisa);
        OperacionEntity operacion = cambioDivisa.getOperacionByOperacion();
        List<CambDivisaEntity> cambios = new ArrayList<>();
        cambios.add(cambioDivisa);
        operacion.setCambDivisasByIdOperacion(cambios);
        operacionEntityRepository.save(operacion);

        /*
         * TODO Por hacer porque me tengo que estudiar los cambios de monedas a monedas
         */
        return "menu";
    }

    @GetMapping("/extraccion")
    public String doExtraccion(Model model, HttpSession session) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        ClienteEntity cliente = usuario.getClienteByCliente();
        List<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
        CuentaEntity cuenta = cuentas.get(0);

        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByCuentaRealiza(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));
        operacionEntityRepository.save(operacion);
        ExtraccionEntity extraccion = new ExtraccionEntity();
        extraccion.setOperacionByOperacion(operacion);
        model.addAttribute("Extraer", extraccion);

        return "extraccion";
    }

    @PostMapping("/guardarExtraccion")
    public String doGuardarExtraccion(@ModelAttribute("Extraer") ExtraccionEntity extra) {
        extraccionEntityRepository.save(extra);
        OperacionEntity operacion = extra.getOperacionByOperacion();
        List<ExtraccionEntity> extrac = new ArrayList<>();
        extrac.add(extra);
        operacion.setExtraccionsByIdOperacion(extrac);
        operacionEntityRepository.save(operacion);

        CuentaEntity mia = operacion.getCuentaByCuentaRealiza();
        Double cantidad = extra.getCantidad();
        if (mia.getSaldo() - cantidad >= 0) {
            mia.setSaldo(mia.getSaldo() - cantidad);
            cuentaEntityRepository.save(mia);
        } else {
            //TODO estas pobre
        }
        return "menu";
    }
}
