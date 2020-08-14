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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.redsocial.dto.RelationshipDto;
import es.eoi.redsocial.dto.UserDto;
import es.eoi.redsocial.dto.UserNotPassDto;
import es.eoi.redsocial.dto.userFull.UserFullDto;
import es.eoi.redsocial.entity.RelationShip;
import es.eoi.redsocial.entity.User;
import es.eoi.redsocial.enums.RelationShipStateEnum;
import es.eoi.redsocial.service.RelationshipService;
import es.eoi.redsocial.service.UserService;
import es.eoi.redsocial.utils.ManagerException;
import es.eoi.redsocial.utils.Transformation;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RelationshipService relationshipService;

	@Autowired
	private ManagerException managerException;

	@Autowired
	private Transformation transform;

	@GetMapping("")
	ResponseEntity<?> getAllUser() {

		List<UserNotPassDto> userDto = null;

		try {

			Optional<List<User>> users = userService.findAllUsers();

			if (users.isPresent()) {
				userDto = transform.listUserEntity_UsernotpassDto(users.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@GetMapping(value = "/search", params = "value")
	ResponseEntity<?> getAllUserByNameSurname(@RequestParam(value = "value") String search) {

		List<UserNotPassDto> userDto = null;

		try {

			Optional<List<User>> users = userService.findAllUsersByNameSurname(search);

			if (users.isPresent()) {
				userDto = transform.listUserEntity_UsernotpassDto(users.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> getUser(@PathVariable(value = "id") Long id) {

		UserNotPassDto userDto = null;

		try {

			Optional<User> user = userService.findUser(id);

			if (user.isPresent()) {
				userDto = transform.userEntity_UsernotpassDto(user.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@GetMapping("/fullUser/{id}")
	ResponseEntity<?> getFullUser(@PathVariable(value = "id") Long id) {

		UserFullDto userDto = null;

		try {

			Optional<User> user = userService.findUser(id);

			if (user.isPresent()) {
				userDto = transform.userEntity_UserfullDto(user.get());

			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDto);
	}

	@PostMapping("")
	ResponseEntity<?> createUser(@RequestBody UserDto userDto) {

		try {

			userService.createUser(transform.userDto_UserEntity(userDto));

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@PutMapping(value = "/{id}", params = "surname")
	ResponseEntity<?> updateUser(
			@RequestParam(value = "surname") String surname,
			@PathVariable(value = "id") Long id) {

		User user = new User();
		user.setId(id);
		user.setSurname(surname);

		try {

			userService.updateUser(user);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@PostMapping("/login")
	ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {

		UserNotPassDto userDtoLogin = null;

		try {

			Optional<User> user = userService.loginUser(transform.userDto_UserEntity(userDto));

			if (user.isPresent()) {
				userDtoLogin = transform.userEntity_UsernotpassDto(user.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(userDtoLogin);
	}

	@GetMapping("/{id}/friendsRelationship")
	ResponseEntity<?> getAllRelationshipFriend(
			@PathVariable(value = "id") Long id) {

		List<RelationshipDto> relationshipDto = null;

		try {

			Optional<List<RelationShip>> relationships = relationshipService.findAllRelationshipsFriendStatus(id);

			if (relationships.isPresent()) {
				relationshipDto = transform.listRelationshipEntity_RelationshipDto(relationships.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(relationshipDto);
	}

	@GetMapping("/{id}/pendingRelationship")
	ResponseEntity<?> getAllRelationshipPending(
			@PathVariable(value = "id") Long id) {

		List<RelationshipDto> relationshipDto = null;

		try {

			Optional<List<RelationShip>> relationships = relationshipService.findAllRelationshipsPendingStatus(id);

			if (relationships.isPresent()) {
				relationshipDto = transform.listRelationshipEntity_RelationshipDto(relationships.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(relationshipDto);
	}

	@PostMapping(value = "/{id}/inviteFriend", params = "idFriend")
	ResponseEntity<?> createRelationshipPending(
			@PathVariable(value = "id") Long idMe,
			@RequestParam(value = "idFriend") Long idFriend) {

		try {

			User userMe = new User();
			userMe.setId(idMe);

			User userFriend = new User();
			userFriend.setId(idFriend);

			RelationShip relationShip = new RelationShip();
			relationShip.setState(RelationShipStateEnum.PENDING);
			relationShip.setUserMe(userMe);
			relationShip.setUserFriend(userFriend);

			if (!relationshipService.createRelationShip(relationShip)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@PutMapping("/relationship/{id}")
	ResponseEntity<?> updateRelationshipStatusToFriend(@PathVariable(value = "id") Long id) {

		try {

			RelationShip relationShip = new RelationShip();
			relationShip.setId(id);
			relationShip.setState(RelationShipStateEnum.FRIEND);

			relationshipService.updateRelationShip(relationShip);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}

	@DeleteMapping("/relationship/{id}")
	ResponseEntity<?> deleteRelationship(@PathVariable(value = "id") Long id) {

		try {

			RelationShip relationShip = new RelationShip();
			relationShip.setId(id);

			relationshipService.deleteRelationShip(relationShip);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return managerException.getExceptionDto(e);
		}

		return ResponseEntity.ok(true);
	}

}
