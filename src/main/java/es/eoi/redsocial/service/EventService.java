package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.custom.EventCustom;

public interface EventService {

	Optional<List<Event>> findAllEvents();

	Optional<Event> findEvent(Long id);

	void createEvent(Event event);
	
	List<EventCustom> top3Assistance();
	
	List<EventCustom> top3LessAssistance();
	
	List<Event> getEventPast();
	
	List<Event> getEventsToday();

	List<Event> findAllEventsUpdate();

	void updateEvents(List<Event> event);
	
	Optional<List<Event>> findAllEventsByUserId(Long userId);
}
