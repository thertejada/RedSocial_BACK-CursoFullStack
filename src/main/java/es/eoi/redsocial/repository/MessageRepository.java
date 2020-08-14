package es.eoi.redsocial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.Message;
import es.eoi.redsocial.enums.RelationShipStateEnum;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Optional<List<Message>> findByUserId(Long user_id);

	@Query("SELECT m"
			+ " FROM Message m"
			+ " LEFT JOIN RelationShip r ON m.user.id = r.userMe.id"
			+ " LEFT JOIN User u ON u.id = r.userFriend.id"
			+ " WHERE u.id = ?1 AND r.state = ?2")
	Optional<List<Message>> findMessageFriends(Long userId, RelationShipStateEnum state);

}
