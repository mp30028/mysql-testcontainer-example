package com.zonesoft.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zonesoft.example.entities.Person;

public interface PersonRepository extends  CrudRepository<Person, Long> {

}
