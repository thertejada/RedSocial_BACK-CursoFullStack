package es.eoi.redsocial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

	Optional<List<Reaction>> findByMessage_Id(Long id);

	Optional<Reaction> findByMessage_IdAndUser_Id(Long messageId, Long UserId);

}
