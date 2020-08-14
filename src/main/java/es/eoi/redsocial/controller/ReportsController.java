package es.eoi.redsocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.redsocial.dto.EventSimpleDto;
import es.eoi.redsocial.dto.UserNotPassDto;
import es.eoi.redsocial.dto.custom.EventCustomDto;
import es.eoi.redsocial.dto.custom.UserCustomDto;
import es.eoi.redsocial.entity.Event;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.entity.custom.EventCustom;
import es.eoi.redsocial.entity.custom.UserCustom;
import es.eoi.redsocial.service.EventService;
import es.eoi.redsocial.service.UserService;
import es.eoi.redsocial.utils.ManagerException;
import es.eoi.redsocial.utils.Transformation;

@RestController
@RequestMapping(value = "/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportsController {

	@Autowired
	private EventService eventSrv;

	@Autowired
	private UserService userSrv;

	@Autowired
	private ManagerException managerException;

	@Autowired
	private Transformation transform;

	// Top 3 asistentes evento

	@GetMapping("/bestEvents")
	ResponseEntity<?> getTop3Assist() {

		List<EventCustomDto> eventDto = null;

		try {

			List<EventCustom> events = eventSrv.top3Assistance();

			if (events != null && events.size() > 0) {
				eventDto = transform.listEventCustomEntity_EventCustomDto(events);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Top 3 menos asistentes evento

	@GetMapping("/worstEvents")
	ResponseEntity<?> getTop3LessAssist() {

		List<EventCustomDto> eventDto = null;

		try {

			List<EventCustom> events = eventSrv.top3LessAssistance();

			if (events != null && events.size() > 0) {
				eventDto = transform.listEventCustomEntity_EventCustomDto(events);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Eventos ya pasados

	@GetMapping("/passedEvents")
	ResponseEntity<?> eventsFinished() {

		List<EventSimpleDto> eventDto = null;

		try {

			List<Event> events = eventSrv.getEventPast();

			if (events != null && events.size() > 0) {
				eventDto = transform.listEventEntity_eventSimpleDto(events);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Eventos de hoy

	@GetMapping("/inProgressEvents")
	ResponseEntity<?> eventsToday() {

		List<EventSimpleDto> eventDto = null;

		try {

			List<Event> events = eventSrv.getEventsToday();

			if (events != null && events.size() > 0) {
				eventDto = transform.listEventEntity_eventSimpleDto(events);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Usuarios + eventos creados

	@GetMapping("/bestActiveUsers")
	ResponseEntity<?> TopUsersEvents() {

		List<UserCustomDto> eventDto = null;

		try {

			List<UserCustom> users = userSrv.getUserTopEvents();

			if (users != null && users.size() > 0) {
				eventDto = transform.listUserCustomEntity_UserCustomDto(users);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Usuarios + messages creados

	@GetMapping("/bestWritterUsers")
	ResponseEntity<?> TopUsersMessages() {

		List<UserCustomDto> eventDto = null;

		try {

			List<UserCustom> users = userSrv.getUserTopMessages();

			if (users != null && users.size() > 0) {
				eventDto = transform.listUserCustomEntity_UserCustomDto(users);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Usuarios + amigos

	@GetMapping("/bestFriendlyUsers")
	ResponseEntity<?> TopUsersFriends() {

		List<UserCustomDto> eventDto = null;

		try {

			List<UserCustom> users = userSrv.getUserTopFriens();

			if (users != null && users.size() > 0) {
				eventDto = transform.listUserCustomEntity_UserCustomDto(users);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Usuarios + asistencias a eventos

	@GetMapping("/bestAssistanceUsers")
	ResponseEntity<?> TopUsersAssistance() {

		List<UserCustomDto> eventDto = null;

		try {

			List<UserCustom> users = userSrv.getUserTopAssist();

			if (users != null && users.size() > 0) {
				eventDto = transform.listUserCustomEntity_UserCustomDto(users);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(eventDto);
	}

	// Usuarios + score

	@GetMapping("/bestScoredUsers")
	ResponseEntity<?> TopUsersScore() {

		List<UserNotPassDto> userDto = null;

		try {

			List<User> users = userSrv.getUserTopScore();

			if (users != null && users.size() > 0) {
				userDto = transform.listUserEntity_UsernotpassDto(users);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

}
