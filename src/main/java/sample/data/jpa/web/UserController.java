package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@RestController
public class UserController {
	
	/**
	 * GET /createUser --> Create a new user and save it in the database.
	 */
	@RequestMapping(value = "/createUser", params = { "email", "name", "password" })
	@ResponseBody
	public String createUser(@RequestParam("email") String email, @RequestParam("name") String name,
			@RequestParam("password") String password) {
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
	 * GET /deleteUser --> Delete the user having the passed id.
	 */
	@RequestMapping(value = "/deleteUser", params = "id")
	@ResponseBody
	public String deleteUser(@RequestParam("id") Long id) {
		try {
			userDao.deleteById(id);
		} catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * GET /getUsers --> Return all users.
	 */
	@RequestMapping(value = "/getUsers")
	@ResponseBody
	public String getUsers() {
		String usersData = "";
		try {
			List<User> users = userDao.findAll();
			for (User user : users) {
				usersData += user.toString() + "\r\n";
				System.out.println(user.toString());
			}
		} catch (Exception ex) {
			return "Users not found";
		}
		return usersData;
	}
	
	/**
	 * GET /getUserByEmail --> Return the id for the user having the passed email.
	 */
	@RequestMapping(value = "/getUserByEmail", params = "email")
	@ResponseBody
	public String getUserByEmail(@RequestParam("email") String email) {
		String usersData = "";
		try {
			User user = userDao.findByEmail(email);
			usersData = user.toString();
		} catch (Exception ex) {
			return "User not found";
		}

		return usersData;
	}

	/**
	 * GET /getUserByName --> Return the id for the user having the passed name.
	 */
	@RequestMapping(value = "/getUserByName", params = "name")
	@ResponseBody
	public String getUserByName(@RequestParam("name") String name) {
		String usersData = "";
		try {
			User user = userDao.findByName(name);
			usersData = user.toString();
		} catch (Exception ex) {
			return "User not found";
		}
		return usersData;
	}

	// Private fields

	@Autowired
	private UserDao userDao;

}