package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{
	
	@Query("SELECT e FROM Evento e WHERE e.destacado = 'S'")
	List<Evento> porDestacado();
	
	@Query("SELECT e FROM Evento e WHERE e.estado = 'ACTIVO'")
	List<Evento> porActivo();

}