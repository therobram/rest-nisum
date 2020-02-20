package com.nisum.rest.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.nisum.rest.config.audit.Auditable;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<String> {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@NotNull
	@Column
	private String name;

	@NotNull
	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;

	@Column
	private String password;

	@Column
	private Boolean isactive = true;

	@Column
	private String token = "";

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_phones", joinColumns = @JoinColumn(name = "user_id"))
	private Set<Phone> phones = new HashSet<>();

	public User() {

	}

	public User(@NotNull String name, @NotNull @Email @Size(max = 100) String email, String password,
			Set<Phone> phones) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}
