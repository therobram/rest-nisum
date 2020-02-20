package com.nisum.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(UUID id);
	
	Optional<User> findByEmail(String email);

}
