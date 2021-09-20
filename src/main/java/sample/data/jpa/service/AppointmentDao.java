package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;

import java.util.Date;
import java.util.Optional;

// Imports ...

@Transactional
public interface AppointmentDao extends JpaRepository<Appointment, Long> {

    /**
     * This method will find an User instance in the database by its email.
     * Note that this method is not implemented and its working code will be
     * automagically generated from its signature by Spring Data JPA.
     */
    public Optional<Appointment> findById(Long id);

    public Optional<Appointment> findByDate(Date date);




}