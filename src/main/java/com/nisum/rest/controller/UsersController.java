package com.nisum.rest.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.rest.dto.UserDTO;
import com.nisum.rest.exception.CreatedUserErrorException;
import com.nisum.rest.exception.UpdateUserErrorException;
import com.nisum.rest.exception.UserNotFoundException;
import com.nisum.rest.model.User;
import com.nisum.rest.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

	private static final Logger LOG = LogManager.getLogger(UsersController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {

		UserDTO userDTO = this.userService.createUser(user);

		if (userDTO.getId() == null) {
			throw new CreatedUserErrorException();
		} else {
			LOG.info("Se crea usuario");
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable UUID userId) {
		User updatedUser = userService.updateUser(user, userId);

		if (updatedUser.getId() == null) {
			throw new UpdateUserErrorException();
		} else {
			LOG.info("Se actualiza usuario: " + userId);
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userById(@PathVariable UUID userId) {

		User user = userService.getByUserId(userId);

		if (user.getId() == null) {
			throw new UserNotFoundException(userId);
		} else {
			LOG.info("userById: " + userId);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> usuarios = userService.getAllUser();
		LOG.info("Listado de todos los usurios");
		return new ResponseEntity<List<User>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUserById(@PathVariable UUID userId) {
		userService.deleteUser(userId);
		LOG.info("deleteUserById: " + userId);
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}

}
