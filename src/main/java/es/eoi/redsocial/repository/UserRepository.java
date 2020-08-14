package es.eoi.redsocial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.entity.custom.UserCustom;
import es.eoi.redsocial.enums.AssistanceStateEnum;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserAndPass(String user, String pass);

	@Query("SELECT u FROM User u"
			+ " LEFT JOIN Assistance a ON a.user.id = u.id"
			+ " LEFT JOIN Event e ON e.id = a.event"
			+ " WHERE e.id = ?1 AND a.state = ?2")
	Optional<List<User>> findAllUsersAssistanceState(Long idAsistance, AssistanceStateEnum state);

	@Query("SELECT new es.eoi.redsocial.entity.custom.UserCustom"
			+ "(u.id, u.name, u.surname, u.birthDate, u.startDate, u.user, u.score, count(e.user) as total)"
			+ " FROM User u"
			+ " LEFT JOIN Event e ON u.id = e.user"
			+ " GROUP BY u.id"
			+ " ORDER BY total DESC")
	List<UserCustom> getUsersMoreEvents(Pageable pageable);

	@Query("SELECT new es.eoi.redsocial.entity.custom.UserCustom"
			+ "(u.id, u.name, u.surname, u.birthDate, u.startDate, u.user, u.score, count(m.user) as total)"
			+ " FROM User u"
			+ " LEFT JOIN Message m ON u.id = m.user"
			+ " GROUP BY u.id"
			+ " ORDER BY total DESC")
	List<UserCustom> getUsersMoreMessages(Pageable pageable);

	@Query("SELECT new es.eoi.redsocial.entity.custom.UserCustom"
			+ "(u.id, u.name, u.surname, u.birthDate, u.startDate, u.user, u.score, count(r.userFriend) as total)"
			+ " FROM User u"
			+ " LEFT JOIN RelationShip r ON u.id = r.userMe"
			+ " GROUP BY u.id"
			+ " ORDER BY total DESC")
	List<UserCustom> getUsersMoreFriends(Pageable pageable);

	@Query("SELECT new es.eoi.redsocial.entity.custom.UserCustom"
			+ "(u.id, u.name, u.surname, u.birthDate, u.startDate, u.user, u.score, count(a.user) as total)"
			+ " FROM User u"
			+ " LEFT JOIN Assistance a ON u.id = a.user"
			+ " WHERE a.state = 1"
			+ " GROUP BY u.id"
			+ " ORDER BY total DESC")
	List<UserCustom> getUsersMoreAssistEvents(Pageable pageable);

	@Query("SELECT u"
			+ " FROM User u"
			+ " ORDER BY u.score DESC")
	List<User> getUsersMoreScored(Pageable pageable);

	@Query("SELECT u FROM User u"
			+ " WHERE CONCAT(user.name,' ', user.surname) LIKE ?1%")
	Optional<List<User>> findAllUsersByNameSurname(String search);

}