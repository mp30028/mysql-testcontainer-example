package com.zonesoft.example.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.zonesoft.example.entities.Person;
import com.zonesoft.example.repositories.PersonRepository;

import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

@Testcontainers()
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PersonRepositoryTest extends AbstractMySqlContainer {

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
	    registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
	    registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);  
	}
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	void testBasicSetup() {
		ScriptUtils.runInitScript(new JdbcDatabaseDelegate(MYSQL_CONTAINER, ""),"initialise_test_db.sql");
		int EXPECTED_SIZE = 6;
//		String FIRSTNAME = "TestFirstname";
		assertNotNull(personRepository);
		Iterable<Person> searchResults = personRepository.findAll();
		List<Person> result = StreamSupport.stream(searchResults.spliterator(), false).collect(Collectors.toList());
		assertEquals(EXPECTED_SIZE, result.size());
//		Person createdPerson = new Person();
//		Person returnedPerson = personRepository.save(createdPerson);
//		assertNotNull(returnedPerson);
//		assertNotNull(returnedPerson.getId());
//		assertEquals(FIRSTNAME, returnedPerson.getFirstname());
	}

}