package br.com.rapha89.repository;

import br.com.rapha89.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {




}
