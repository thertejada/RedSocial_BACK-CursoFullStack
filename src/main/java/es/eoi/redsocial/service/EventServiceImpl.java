package es.eoi.redsocial.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.custom.EventCustom;
import es.eoi.redsocial.enums.EventStateEnum;
import es.eoi.redsocial.repository.EventRepository;
import es.eoi.redsocial.service.UserServiceImpl.IncremetScoreType;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private UserService userService;

	@Override
	public Optional<List<Event>> findAllEvents() {
		return Optional.of(repository.findAll());
	}

	@Override
	public Optional<Event> findEvent(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void createEvent(Event event) {
		event.setState(EventStateEnum.SCHEDULED);
		repository.save(event);
		userService.incrementScoreUser(event.getUser(), IncremetScoreType.EVENT);
	}

	@Override
	public List<EventCustom> top3Assistance() {
		return repository.getCountTop3Assist(PageRequest.of(0, 3));

	}

	@Override
	public List<EventCustom> top3LessAssistance() {
		return repository.getCountTop3LessAssist(PageRequest.of(0, 3));
	}

	@Override
	public List<Event> getEventPast() {
		return repository.getPastEvents();
	}

	@Override
	public List<Event> getEventsToday() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // Los meses van del 0 al 11
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		return repository.getTodayEvents(
				Timestamp.valueOf(LocalDate.of(year, month, day).atStartOfDay()),
				Timestamp.valueOf(String.format("%04d-%02d-%02d 23:59:59", year, month, day)));
	}

	@Override
	public void updateEvents(List<Event> event) {
		repository.saveAll(event);
	}

	@Override
	public List<Event> findAllEventsUpdate() {
		return repository.getNotPassedEvents();
	}

	@Override
	public Optional<List<Event>> findAllEventsByUserId(Long userId) {
		return repository.findByUser_Id(userId);
	}

}
