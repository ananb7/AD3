package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.repository.EventoRepository;

@Repository
public class EventoDaoImplMy8 implements EventoDao{

		@Autowired
		private EventoRepository eRepo;

		@Override
		public List<Evento> todos() {
			
			return eRepo.findAll();
		}

		@Override
		public Evento porId(int idEvento) {
			
			return eRepo.findById(idEvento).orElse(null);
		}

		@Override
		public List<Evento> porDestacado() {
			
			return eRepo.porDestacado();
		}

		@Override
		public List<Evento> porActivo() {
			
			return eRepo.porActivo();
		}

}
