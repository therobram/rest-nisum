package com.nisum.rest.service;

import java.util.List;
import java.util.UUID;

import com.nisum.rest.dto.UserDTO;
import com.nisum.rest.model.User;

public interface UserService {

	UserDTO createUser(User user);

	User updateUser(User user, UUID userId);

	List<User> getAllUser();

	User getByUserId(UUID userId);

	void deleteUser(UUID userId);
}
