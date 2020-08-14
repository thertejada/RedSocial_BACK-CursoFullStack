package es.eoi.redsocial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.redsocial.dto.MessageDto;
import es.eoi.redsocial.dto.ReactionDto;
import es.eoi.redsocial.entity.Message;
import es.eoi.redsocial.entity.Reaction;
import es.eoi.redsocial.service.MessageService;
import es.eoi.redsocial.service.ReactionService;
import es.eoi.redsocial.utils.ManagerException;
import es.eoi.redsocial.utils.Transformation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

	@Autowired
	private MessageService messageSrv;

	@Autowired
	private ReactionService reactionSrv;

	@Autowired
	private ManagerException managerException;

	@Autowired
	private Transformation transform;

	// Devolverá el POST solicitado

	@GetMapping("/messages/{id}")
	public ResponseEntity<?> getByIdMessage(@PathVariable Long id) {

		try {

			Optional<Message> messageEntity = messageSrv.finByIdMessage(id);
			if (messageEntity == null) {
				return ResponseEntity.notFound().build();
			}
			MessageDto messageId = transform.messageEntity_MessageDto(messageEntity.get());

			return ResponseEntity.ok(messageId);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}
	}

	// Crea un nuevo POST

	@PostMapping("/messages")
	public ResponseEntity<?> createMessage(@RequestBody MessageDto message) {

		try {

			messageSrv.saveMessage(transform.messageDto_MessageEntity(message));
			return ResponseEntity.status(HttpStatus.CREATED).body(true);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}
	}

	// Devolverá la lista de POSTs publicados por un usuario

	@GetMapping("/messages/user/{id}")
	public ResponseEntity<?> getAllMessagesByUser(@PathVariable Long id) {

		List<MessageDto> messageDto = null;

		try {

			Optional<List<Message>> messagesUser = messageSrv.findAllMessagesByUser(id);

			if (messagesUser.isPresent()) {
				messageDto = transform.listMessageEntity_MessageDto(messagesUser.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(messageDto);

	}

	// Devolverá los post de los amigos de un usuario

	@GetMapping("/messages/user/{id}/friendPost")
	public ResponseEntity<?> getAllMessagesOfFriends(@PathVariable Long id) {

		List<MessageDto> messageDto = null;

		try {

			Optional<List<Message>> messagesFriends = messageSrv.findAllMessagesFriendsOfUser(id);

			if (messagesFriends.isPresent()) {
				messageDto = transform.listMessageEntity_MessageDto(messagesFriends.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(messageDto);

	}

	// Devolverá las reacciones a un post

	@GetMapping("/messages/{id}/reactions")
	ResponseEntity<?> getAllRelationshipFriend(@PathVariable Long id) {

		List<ReactionDto> reactionsDto = null;

		try {

			Optional<List<Reaction>> reactions = reactionSrv.findAllReactionOfMessage(id);

			if (reactions.isPresent()) {
				reactionsDto = transform.listReactionEntity_ReactionDto(reactions.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(reactionsDto);
	}

	// Crear reaccion

	@PostMapping("/messages/{id}/reactions")
	public ResponseEntity<?> createReaction(@RequestBody ReactionDto reaction, @PathVariable Long id) {

		try {

			Reaction r = transform.reactionDto_ReactionEntity(reaction);
			Message m = new Message();
			m.setId(id);
			r.setMessage(m);
			reactionSrv.createReaction(r);
			return ResponseEntity.status(HttpStatus.CREATED).body(true);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}
	}

	// Borra un POST

	@DeleteMapping("/messages/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable Long id) {

		try {

			messageSrv.deleteByIdMessage(id);
			return ResponseEntity.noContent().build();

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}
	}

}
