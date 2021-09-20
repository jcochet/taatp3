package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;

import java.util.Optional;

// Imports ...

@Transactional
public interface WorkerDao extends JpaRepository<Worker, Long> {

	/**
	 * This method will find an User instance in the database by its email. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */
	public Worker findByEmail(String email);

	public Worker findByName(String name);

	public Worker findByJob(String Job);

}