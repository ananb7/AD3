package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entitis.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

}