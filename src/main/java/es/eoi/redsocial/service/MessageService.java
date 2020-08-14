package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.Message;

public interface MessageService {
	
	// Devolverá el POST solicitado
	Optional<Message> finByIdMessage(Long id);
	
	// Devolverá los mensajes del usuario
	Optional<List<Message>> findAllMessagesByUser(Long userId);
	
	// Devolverá los mensajes de los amigos del usuario
	Optional<List<Message>> findAllMessagesFriendsOfUser(Long userId);
	
	// Crea un nuevo POST
	Message saveMessage(Message message);
	
	//Elimina un POST
	void deleteByIdMessage(Long id);
	


}
