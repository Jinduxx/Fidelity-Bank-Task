package com.example.appuser.repository;

import com.example.appuser.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String userName);

    Optional<Person> findByEmail(String email);
}
