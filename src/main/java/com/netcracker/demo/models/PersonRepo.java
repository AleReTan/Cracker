package com.netcracker.demo.models;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository <Person, Integer> {
}
