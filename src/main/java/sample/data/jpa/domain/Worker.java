package sample.data.jpa.domain;
// Imports ...

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.istic.taa.jaxrs.domain.Appointment;

@Entity
@Table(name = "workers")
public class Worker extends User {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String job;

	// Public methods

	public Worker() {
	}

	public Worker(long id) {
		this.id = id;
	}

	public Worker(String email, String name, String password, String job) {
		this(email, name, password, job, new ArrayList<Appointment>());
	}

	public Worker(String email, String name, String password, String job, List<Appointment> appointments) {
		super(email, name, password, appointments);
		this.job = job;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		String str = "User [id=" + id + ", name=" + getName() + ", email=" + getEmail() + ", password=" + getPassword()
				+ ", job=" + job + ", appointments={";
		for (Appointment appointment : getAppointments()) {
			str += appointment.getId() + ",";
		}
		str += "}]";
		return str;
	}

}