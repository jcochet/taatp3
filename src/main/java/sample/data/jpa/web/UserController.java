package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@Controller
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
	public String deleteUser(@RequestParam("id") long id) {
		try {
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * GET /getUserByEmail --> Return the id for the user having the passed email.
	 */
	@RequestMapping("/getUserByEmail/{email}")
	@ResponseBody
	public String getUserByEmail(@PathVariable("email") String email) {
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
	 * GET /getUserByName --> Return the id for the user having the passed name.
	 */
	@RequestMapping("/getUserByName/{name}")
	@ResponseBody
	public String getUserByName(@PathVariable("name") String name) {
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
	 * GET /updateUser --> Update the email and the name for the user in the
	 * database having the passed id.
	 */
	@RequestMapping(value = "/updateUser/{id}", params = { "email", "name", "password" })
	@ResponseBody
	public String updateUser(@PathVariable("id") long id, @RequestParam("email") String email,
			@RequestParam("name") String name, @RequestParam("password") String password) {
		try {
			User user = userDao.findById(id).get();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
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