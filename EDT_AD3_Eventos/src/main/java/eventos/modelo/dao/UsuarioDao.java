package eventos.modelo.dao;

import eventos.modelo.entitis.Usuario;

public interface UsuarioDao {

	Usuario findById(String username);
	boolean registro(Usuario usuario);

}