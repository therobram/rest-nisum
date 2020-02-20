package com.nisum.rest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.rest.dto.UserDTO;
import com.nisum.rest.exception.EmailExistsNotFoundException;
import com.nisum.rest.exception.EmailInvalidNotFoundException;
import com.nisum.rest.exception.PasswordInvalidNotFoundException;
import com.nisum.rest.exception.UserNotFoundException;
import com.nisum.rest.model.User;
import com.nisum.rest.repository.UserRepository;
import com.nisum.rest.service.UserService;
import com.nisum.rest.validator.EmailValidator;
import com.nisum.rest.validator.PasswordValidator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(User user) {

		UserDTO userDTO = new UserDTO();

		if (validateCreationUser(user)) {

			User userSave = userRepository.save(user);

			ModelMapper modelMapper = new ModelMapper();
			userDTO = modelMapper.map(userSave, UserDTO.class);
		}
		return userDTO;

	}

	@Override
	public User updateUser(User user, UUID userId) {
		User userUpdate = new User();

		Optional<User> userDb = this.userRepository.findById(userId);

		if (userDb.isPresent() && validateEmailAndPassword(user)) {
			userUpdate = userDb.get();
			userUpdate.setName(user.getName());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPassword(user.getPassword());
			userUpdate.setPhones(user.getPhones());
			userRepository.save(userUpdate);
		}
		return userUpdate;
	}

	@Override
	public User getByUserId(UUID userId) {

		User userResponse = new User();

		Optional<User> userDb = this.userRepository.findById(userId);

		if (userDb.isPresent()) {
			userResponse = userDb.get();
		}
		return userResponse;
	}

	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public void deleteUser(UUID userId) {
		Optional<User> userDb = this.userRepository.findById(userId);

		if (userDb.isPresent()) {
			this.userRepository.delete(userDb.get());
		} else {
			throw new UserNotFoundException(userId);
		}

	}

	private Boolean validateCreationUser(User user) {

		PasswordValidator passwordValidator = new PasswordValidator();
		EmailValidator emailValidator = new EmailValidator();

		Boolean emailValid = emailValidator.validate(user.getEmail());
		LOG.info("emailValid: " + emailValid);

		if (!emailValid) {
			throw new EmailInvalidNotFoundException(user.getEmail());
		}

		Boolean passValid = passwordValidator.validate(user.getPassword());
		LOG.info("passValid: " + passValid);

		if (!passValid) {
			throw new PasswordInvalidNotFoundException(user.getPassword());
		}

		Boolean emailExiste = this.userRepository.findByEmail(user.getEmail()).isPresent();
		LOG.info("emailExiste: " + emailExiste);

		if (emailExiste) {
			throw new EmailExistsNotFoundException(user.getEmail());
		}

		if (!emailExiste && passValid && emailValid) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean validateEmailAndPassword(User user) {

		PasswordValidator passwordValidator = new PasswordValidator();
		EmailValidator emailValidator = new EmailValidator();

		Boolean emailValid = emailValidator.validate(user.getEmail());
		LOG.info("emailValid: " + emailValid);

		if (!emailValid) {
			throw new EmailInvalidNotFoundException(user.getEmail());
		}

		Boolean passValid = passwordValidator.validate(user.getPassword());
		LOG.info("passValid: " + passValid);

		if (!passValid) {
			throw new PasswordInvalidNotFoundException(user.getPassword());
		}

		if (passValid && emailValid) {
			return true;
		} else {
			return false;
		}
	}

}
