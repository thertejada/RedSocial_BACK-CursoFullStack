package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.redsocial.entity.Message;
import es.eoi.redsocial.enums.RelationShipStateEnum;
import es.eoi.redsocial.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepo;

	@Override
	public Optional<List<Message>> findAllMessagesByUser(Long userId) {
		return messageRepo.findByUserId(userId);
	}

	@Override
	@Transactional
	public void deleteByIdMessage(Long id) {
		messageRepo.deleteById(id);
	}

	@Override
	public Optional<Message> finByIdMessage(Long id) {

		return messageRepo.findById(id);
	}

	@Override
	public Message saveMessage(Message message) {

		return messageRepo.save(message);
	}

	@Override
	public Optional<List<Message>> findAllMessagesFriendsOfUser(Long userId) {

		return messageRepo.findMessageFriends(userId, RelationShipStateEnum.FRIEND);
	}

}
