package sample.data.jpa.service;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Appointment;

// Imports ...

@Transactional
public interface AppointmentDao extends JpaRepository<Appointment, Long> {

	public Appointment findByDate(Date date);
	
}