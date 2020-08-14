package es.eoi.redsocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.Assistance;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {

	Optional<Assistance> findByUser_IdAndEvent_id(Long userId, Long eventId);

}
