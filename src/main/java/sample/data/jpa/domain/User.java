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


@Entity
@Table(name = "users")
public class User {

	// An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String password;

	private List<Long> appointments = new ArrayList<Long>();

	// Public methods

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String email, String name, String password) {
		this(email, name, password, new ArrayList<Long>());
	}

	public User(String email, String name, String password, List<Long> appointments) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.appointments = appointments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Long> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Long> appointments) {
		this.appointments = appointments;
	}
}