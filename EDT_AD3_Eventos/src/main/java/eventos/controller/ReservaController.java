package eventos.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservaController {
	
	@Autowired
	private EventoDao eDao;
	@Autowired
    private ReservaDao rDao;

    @PostMapping("/reservar/{idEvento}")
    public String reservarEntradas(@PathVariable("idEvento") int idEvento, 
                                   @RequestParam("cantidad") int cantidad, 
                                   HttpSession session) {
        // Obtenemos el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        // Creamos una nueva reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        // Aquí deberías obtener el evento correspondiente al idEvento
        Evento evento = eDao.porId(idEvento);
        reserva.setEvento(evento);
        reserva.setCantidad(cantidad);
        // Guardamos la reserva en la base de datos
        rDao.guardar(reserva);
        // Redirigimos a la página de detalles del evento
        return "redirect:/eventos/detalle/" + idEvento;
    }

    @ModelAttribute("cantidades")
    public List<Integer> getCantidadesDisponibles() {
        List<Integer> cantidades = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            cantidades.add(i);
        }
        return cantidades;
    }
}