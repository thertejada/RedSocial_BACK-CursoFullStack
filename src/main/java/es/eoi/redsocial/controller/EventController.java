package es.eoi.redsocial.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.redsocial.dto.EventDto;
import es.eoi.redsocial.dto.EventSimpleDto;
import es.eoi.redsocial.dto.UserSimpleDto;
import es.eoi.redsocial.entity.Assistance;
import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.enums.AssistanceStateEnum;
import es.eoi.redsocial.enums.EventStateEnum;
import es.eoi.redsocial.service.AssistanceService;
import es.eoi.redsocial.service.EventService;
import es.eoi.redsocial.utils.ManagerException;
import es.eoi.redsocial.utils.Transformation;

@RestController
@RequestMapping(value = "/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private AssistanceService assistanceService;

	@Autowired
	private ManagerException managerException;

	@Autowired
	private Transformation transform;

	@GetMapping("")
	ResponseEntity<?> getAllEvent() {

		List<EventSimpleDto> eventDto = null;

		try {

			Optional<List<Event>> events = eventService.findAllEvents();

			if (events.isPresent()) {
				eventDto = transform.listEventEntity_eventSimpleDto(events.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	@GetMapping("/user/{id}")
	ResponseEntity<?> getAllEventByUser(@PathVariable(value = "id") Long userId) {

		List<EventDto> eventDto = null;

		try {

			Optional<List<Event>> events = eventService.findAllEventsByUserId(userId);

			if (events.isPresent()) {
				eventDto = transform.listEventEntity_EventDto(events.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> getEvent(@PathVariable(value = "id") Long id) {

		EventDto eventDto = null;

		try {

			Optional<Event> events = eventService.findEvent(id);

			if (events.isPresent()) {
				eventDto = transform.eventEntity_EventDto(events.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	@PostMapping("")
	ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {

		try {

			eventService.createEvent(transform.eventDto_EventEntity(eventDto));

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@PostMapping(value = "/{id}/yesAssistance", params = "user")
	ResponseEntity<?> createYesAssistance(
			@PathVariable(value = "id") Long eventId,
			@RequestParam(value = "user") Long userId) {

		Assistance assistance = new Assistance();
		Event event = new Event();
		event.setId(eventId);
		User user = new User();
		user.setId(userId);

		assistance.setEvent(event);
		assistance.setUser(user);
		assistance.setState(AssistanceStateEnum.YES);

		try {

			assistanceService.createUpdateAssitance(assistance);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@PostMapping(value = "/{id}/notAssistance", params = "user")
	ResponseEntity<?> createNotAssistance(
			@PathVariable(value = "id") Long eventId,
			@RequestParam(value = "user") Long userId) {

		Assistance assistance = new Assistance();
		Event event = new Event();
		event.setId(eventId);
		User user = new User();
		user.setId(userId);

		assistance.setEvent(event);
		assistance.setUser(user);
		assistance.setState(AssistanceStateEnum.NO);

		try {

			assistanceService.createUpdateAssitance(assistance);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@GetMapping("/{id}/users/yesAssistance")
	ResponseEntity<?> getAllUsersYesAssistance(@PathVariable(value = "id") Long id) {

		List<UserSimpleDto> userDto = null;

		try {

			Optional<List<User>> users = assistanceService.getAllUsersAssistanceByState(id, AssistanceStateEnum.YES);

			if (users.isPresent()) {
				userDto = transform.listUserEntity_userSimpleDto(users.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/{id}/users/notAssistance")
	ResponseEntity<?> getAllUsersNotAssistance(@PathVariable(value = "id") Long id) {

		List<UserSimpleDto> userDto = null;

		try {

			Optional<List<User>> users = assistanceService.getAllUsersAssistanceByState(id, AssistanceStateEnum.NO);

			if (users.isPresent()) {
				userDto = transform.listUserEntity_userSimpleDto(users.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/user/{id}/yesAssistance")
	ResponseEntity<?> getAllEventsYesAssistance(@PathVariable(value = "id") Long id) {

		List<EventDto> eventDto = null;

		try {

			Optional<List<Event>> events = assistanceService.getAllAssistanceByUserAndState(id,
					AssistanceStateEnum.YES);

			if (events.isPresent()) {
				eventDto = transform.listEventEntity_EventDto(events.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	@GetMapping("/user/{id}/notAssistance")
	ResponseEntity<?> getAllEventsNotAssistance(@PathVariable(value = "id") Long id) {

		List<EventDto> eventDto = null;

		try {

			Optional<List<Event>> events = assistanceService.getAllAssistanceByUserAndState(id,
					AssistanceStateEnum.NO);

			if (events.isPresent()) {
				eventDto = transform.listEventEntity_EventDto(events.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	@PutMapping("/refreshEventState")
	@Scheduled(cron = "0 * * * * ?", zone = "Europe/Paris") // Cada hora
	ResponseEntity<?> updateStateEvent() {

		try {

			List<Event> eventList = eventService.findAllEventsUpdate();

			if (eventList.size() >= 0) {

				for (Event evento : eventList) {
					Date currentDate = timestampToDate(new Timestamp(System.currentTimeMillis()));
					Date eventDate = timestampToDate(evento.getEventDate());

					if (eventDate.after(currentDate)) {
						evento.setState(EventStateEnum.SCHEDULED);

					} else if (eventDate.equals(currentDate)) {
						evento.setState(EventStateEnum.IN_PROGRESS);

					} else {
						evento.setState(EventStateEnum.PASSED);
					}
				}

				eventService.updateEvents(eventList);

			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(true);
	}

	private Date timestampToDate(Timestamp timestamp) {
		return Date.valueOf(timestamp.toLocalDateTime().toLocalDate());
	}

}
