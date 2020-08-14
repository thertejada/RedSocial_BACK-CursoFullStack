package es.eoi.redsocial.service;

import java.util.List;
import java.util.Optional;

import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.entity.custom.UserCustom;
import es.eoi.redsocial.service.UserServiceImpl.IncremetScoreType;

public interface UserService {

	Optional<List<User>> findAllUsers();
	
	Optional<List<User>> findAllUsersByNameSurname(String search);

	Optional<User> findUser(Long id);

	void createUser(User user);

	void updateUser(User user);

	Optional<User> loginUser(User user);

	List<UserCustom> getUserTopEvents();

	List<UserCustom> getUserTopMessages();

	List<UserCustom> getUserTopFriens();

	List<UserCustom> getUserTopAssist();

	void incrementScoreUser(User user, IncremetScoreType type);

	List<User> getUserTopScore();
}
