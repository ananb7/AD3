package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.entitis.Evento;

@Controller
public class EventosController {
	
	
	@Autowired
	private EventoDao eDao;
	
	@GetMapping("/eventos")
	public String verTodos(Model model, Authentication aut) {
		
		List<Evento> evento = eDao.todos();
		model.addAttribute("evento", evento);
		
		return "eventos";
	}

	@GetMapping("/eventos/activos")
	public String verActivos(Model model) {
		List<Evento> evento = eDao.porActivo();
		model.addAttribute("evento", evento);
		
		return "activos";
	}
	
	
	@GetMapping("/eventos/destacados")
	public String verDestacados(Model model) {
		List<Evento> evento = eDao.porDestacado();
		model.addAttribute("evento", evento);
		
		
		return "destacados";
	}
	
	@GetMapping("/eventos/detalle/{idEvento}")
	public String verEvento(@PathVariable("idEvento") int idEvento, Model model) {
	    Evento evento = eDao.porId(idEvento);
	    if (evento != null) {
	        model.addAttribute("evento", evento);
	    } else {
	        model.addAttribute("ms", "Evento no Existe");
	    }
	        return "detalle";
	}
	
	@GetMapping("/detalle")
	public String verDetalle() {
		
		return "detalle";
	}
	
	@GetMapping("/misReservas")
	public String verTodos4() {
		
		return "MisReservas";
	}
	
	@PostMapping("/cancelar")
	public String verTodos5() {
		
		return "MisReservas";
	}


}
