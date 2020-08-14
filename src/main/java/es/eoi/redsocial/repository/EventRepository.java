package es.eoi.redsocial.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.custom.EventCustom;
import es.eoi.redsocial.enums.AssistanceStateEnum;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("SELECT e"
			+ " FROM Event e"
			+ " LEFT JOIN Assistance a ON a.event.id = e.id"
			+ " LEFT JOIN User u ON u.id = a.user.id"
			+ " WHERE u.id = ?1 AND a.state = ?2")
	Optional<List<Event>> getAllAssistanceByUserAndAssistanceState(Long idUser, AssistanceStateEnum state);

	@Query("SELECT new es.eoi.redsocial.entity.custom.EventCustom"
			+ "(e.id, e.name, e.description, e.eventDate, e.user, e.state, count(a.event) as total)"
			+ " FROM Event e"
			+ " LEFT JOIN Assistance a ON e.id = a.event"
			+ " GROUP BY e.id"
			+ " ORDER BY total desc")
	List<EventCustom> getCountTop3Assist(Pageable pageable);

	@Query("SELECT new es.eoi.redsocial.entity.custom.EventCustom"
			+ "(e.id, e.name, e.description, e.eventDate, e.user, e.state,count(a.event) as total)"
			+ " FROM Event e"
			+ " LEFT JOIN Assistance a ON e.id = a.event"
			+ " GROUP BY e.id"
			+ " ORDER BY total asc")
	List<EventCustom> getCountTop3LessAssist(Pageable pageable);

	@Query("SELECT e"
			+ " FROM Event e"
			+ " WHERE e.eventDate < CURRENT_DATE")
	List<Event> getPastEvents();

	@Query("SELECT e"
			+ " FROM Event e"
			+ " WHERE e.eventDate BETWEEN ?1 AND ?2")
	List<Event> getTodayEvents(Timestamp todayMin, Timestamp todayMax);

	@Query("SELECT e"
			+ " FROM Event e"
			+ " WHERE e.state = 0 OR e.state = 1")
	List<Event> getNotPassedEvents();
	
	Optional<List<Event>> findByUser_Id(Long idUser);
}
