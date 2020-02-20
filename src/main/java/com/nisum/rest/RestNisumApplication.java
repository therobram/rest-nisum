package com.nisum.rest;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.nisum.rest.model.Phone;
import com.nisum.rest.model.User;
import com.nisum.rest.repository.UserRepository;

@SpringBootApplication
public class RestNisumApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestNisumApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	//USUARIO DE PRUEBA AL CREARSE LA BASE DE DATOS
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

		Set<Phone> phones = new HashSet<>();
		phones.add(new Phone("31131650", "9", "+56"));
		phones.add(new Phone("24556677", "32", "+56"));

		User user = new User("Roberto Ramirez", "rramirezr2005@gmail.com", "c7d5ae96-276b-427d-b0e0-3facb7fb5ed4", phones);

		userRepository.save(user);
	}

}
