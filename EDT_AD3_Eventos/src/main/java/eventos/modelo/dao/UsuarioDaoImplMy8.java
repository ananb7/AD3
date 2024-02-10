package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.UsuarioRepository;


@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao{

	
	@Autowired
	private UsuarioRepository uRepo;
	

	@Override
	public Usuario findById(String username) {
		return uRepo.findById(username).orElse(null);
	}
	
	@Override
	public boolean registro(Usuario usuario) {
		if (findById(usuario.getUsername()) == null) {
				uRepo.save(usuario);
				return true;
		}
		return false;
	}
}
