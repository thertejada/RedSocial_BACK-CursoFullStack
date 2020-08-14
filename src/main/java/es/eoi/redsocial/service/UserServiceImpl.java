package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.entity.custom.UserCustom;
import es.eoi.redsocial.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * Enum para saber cuanto incrementar el score. EVENT: Un usuario crea un
	 * evento. ASSISTANCE: Un usuario asiste a un evento. ASSISTANCE_CREATOR: El
	 * usuario creador del evento de ASSISTANCE.
	 */
	public enum IncremetScoreType {
		EVENT, ASSISTANCE, ASSISTANCE_CREATOR
	}

	@Autowired
	private UserRepository repository;

	@Override
	public Optional<List<User>> findAllUsers() {
		return Optional.of(repository.findAll());
	}

	@Override
	public Optional<User> findUser(Long id) {
		return repository.findById(id);
	}

	@Override
	public void createUser(User user) {
		user.setScore(0);
		repository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// Solo se permite actualizar el surname y el score
		User u = repository.getOne(user.getId());
		if (user.getSurname() != null) {
			u.setSurname(user.getSurname());
		}
		if (user.getScore() != null) {
			u.setScore(user.getScore());
		}
		repository.save(u);
	}

	@Override
	public Optional<User> loginUser(User user) {
		return repository.findByUserAndPass(user.getUser(), user.getPass());
	}

	@Override
	public List<UserCustom> getUserTopEvents() {

		return repository.getUsersMoreEvents(PageRequest.of(0, 3));
	}

	@Override
	public List<UserCustom> getUserTopMessages() {

		return repository.getUsersMoreMessages(PageRequest.of(0, 3));
	}

	@Override
	public List<UserCustom> getUserTopFriens() {

		return repository.getUsersMoreFriends(PageRequest.of(0, 3));
	}

	@Override
	public List<UserCustom> getUserTopAssist() {

		return repository.getUsersMoreAssistEvents(PageRequest.of(0, 3));
	}

	@Override
	public void incrementScoreUser(User user, IncremetScoreType type) {
		User u = findUser(user.getId()).get();

		if (type == IncremetScoreType.EVENT) {

			if (u.getCreateEvents().size() <= 10) {
				user.setScore(u.getScore() + 10);
			} else {
				user.setScore(u.getScore() + 100);
			}

		} else if (type == IncremetScoreType.ASSISTANCE) {

			user.setScore(u.getScore() + 1);

		} else if (type == IncremetScoreType.ASSISTANCE_CREATOR) {

			if (u.getCreateEvents().size() <= 100) {
				user.setScore(u.getScore() + 1);
			} else {
				user.setScore(u.getScore() + 5);
			}
		}

		updateUser(user);
	}

	@Override
	public List<User> getUserTopScore() {
		return repository.getUsersMoreScored(PageRequest.of(0, 3));
	}

	@Override
	public Optional<List<User>> findAllUsersByNameSurname(String search) {
		return repository.findAllUsersByNameSurname(search);
	}

}
