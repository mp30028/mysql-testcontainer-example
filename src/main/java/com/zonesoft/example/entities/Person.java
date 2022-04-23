package com.zonesoft.example.entities;

import java.time.LocalDate;
//import java.util.List;

import javax.persistence.Column;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="t_person")
public class Person {
	private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);
	private Long id;
	private String firstname;
	private String lastname;
	private LocalDate dateOfBirth;
//	private List<OtherName> otherNames;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "person_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name = "birth_date")
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

//    @OneToMany(mappedBy = "t_person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	public List<OtherName> getOtherNames() {
//		return otherNames;
//	}
//
//	public void setOtherNames(List<OtherName> otherNames) {
//		this.otherNames = otherNames;
//	}
	
	@JsonIgnore
	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			String message = "<EXCEPTION - whilst writing Person Object to JSON. " + e.getLocalizedMessage() + ">" ;
			LOGGER.error(message);
			return message;
		}
		return json;
	}
}
