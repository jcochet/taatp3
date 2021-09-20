package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@Controller
public class UserController {

	/**
	 * GET /create --> Create a new user and save it in the database.
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(String email, String name, String password) {
		String userId = "";
		try {
			User user = new User(email, name, password);
			userDao.save(user);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created with id = " + userId;
	}

	/**
	 * GET /delete --> Delete the user having the passed id.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * GET /get-by-email --> Return the id for the user having the passed email.
	 */
	@RequestMapping("/get-by-email/{email}")
	@ResponseBody
	public String getByEmail(@PathVariable("email") String email) {
		String userId = "";
		try {
			User user = userDao.findByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	/**
	 * GET /get-by-name --> Return the id for the user having the passed name.
	 */
	@RequestMapping("/get-by-name/{name}")
	@ResponseBody
	public String getByName(@PathVariable("name") String name) {
		String userId = "";
		try {
			User user = userDao.findByName(name);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	/**
	 * GET /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(long id, String email, String name, String password, List<Appointment> appointments) {
		try {
			User user = userDao.findById(id).get();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			user.setAppointments(appointments);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	// Private fields

	@Autowired
	private UserDao userDao;

}