package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;

public interface EventoDao {

	List<Evento> todos();
	Evento porId(int idEvento);
	List<Evento> porDestacado();
	List<Evento> porActivo();


}
