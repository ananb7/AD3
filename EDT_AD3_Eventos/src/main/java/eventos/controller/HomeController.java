package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.PerfilDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Perfil;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private EventoDao eDao;
	@Autowired
	private UsuarioDao uDao;
	@Autowired
	private PerfilDao pDao;
	
	@GetMapping("/")
	public String verTodos(Model model, Authentication aut) {
		
		List<Evento> evento = eDao.todos();
		model.addAttribute("evento", evento);
		
		return "home";
	}
	
	@GetMapping("/login")
	public String mostrarFormularioLogin(Model model) {
	    model.addAttribute("usuario", new Usuario()); 
	    return "login";
	}
	
	@GetMapping("/error")
	public String procesarError() {
		
		
		return "login";
	}
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }

	
	// Esta ruta se ejecuta una vez cuando vienes del login, 
	// meto el usuario y me voy a: /
	@GetMapping("/inicioSesion")
	public String procesarLogin(Authentication aut, Model model, HttpSession misesion) {
		
		Usuario usuario = uDao.findById(aut.getName());
		
		if (misesion.getAttribute("usuario") == null)
			misesion.setAttribute("usuario", usuario);
	
		
		return "forward:/";
	}
	

	
	@GetMapping("/registro")
	public String registrar(Model model) {
		 
		model.addAttribute("usuario", new Usuario());
		
		return "registro";	
	}
	
	
	// Guardamos en la BBDD al nuevo usuario y le asignamos el rol_cliente (3)
	@PostMapping("/registro")
	public String registrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
	 	usuario.setPassword("{noop}"+ usuario.getPassword());
	 	Perfil perfil = pDao.findById(3);
	    usuario.getPerfiles().add(perfil);

	 	if (uDao.registro(usuario)) {
	 		ratt.addFlashAttribute("mensaje", "alta realizada");
	 		return "redirect:/login";
	 	}
	 	else {
	 		model.addAttribute("mensaje", "ya existe como usuario");
	 		return "/registro";
	 		
	 	}
		
	}

}
