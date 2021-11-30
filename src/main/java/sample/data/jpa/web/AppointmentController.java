package sample.data.jpa.web;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;
import sample.data.jpa.service.UserDao;
import sample.data.jpa.service.WorkerDao;

@RestController
public class AppointmentController {

	/**
	 * GET /createAppointment --> Create a new appointment and save it in the
	 * database.
	 */
	@RequestMapping(value = "/createAppointment", params = { "date", "duration", "userId", "workerId", "description" })
	@ResponseBody
	public String createAppointment(@RequestParam("date") Date date, @RequestParam("duration") int duration,
			@RequestParam("userId") Long userId, @RequestParam("workerId") Long workerId,
			@RequestParam("description") String description) {
		String appointmentId = "";
		try {
			User user = userDao.getById(userId);
			Worker worker = workerDao.getById(workerId);
			Appointment appointment = new Appointment(date, duration, user, worker, description);
			appointmentDao.save(appointment);
			appointmentId = String.valueOf(appointment.getId());
		} catch (Exception ex) {
			return "Error creating the appointment: " + ex.toString();
		}
		return "Appointment succesfully created with id = " + appointmentId;
	}

	/**
	 * GET /deleteAppointment --> Delete the appointment having the passed id.
	 */
	@RequestMapping(value = "/deleteAppointment", params = "id")
	@ResponseBody
	public String deleteAppointment(@RequestParam("id") Long id) {
		try {
			Appointment appointment = new Appointment(id);
			appointmentDao.delete(appointment);
		} catch (Exception ex) {
			return "Error deleting the appointment:" + ex.toString();
		}
		return "Appointment succesfully deleted!";
	}
	
	/**
	 * GET /getUsers --> Return all users.
	 */
	@RequestMapping(value = "/getAppointements")
	@ResponseBody
	public String getUsers() {
		String appointmentData = "";
		try {
			List<Appointment> appointments = appointmentDao.findAll();
			for (Appointment appointment : appointments) {
				appointmentData += appointment.toString() + "\r\n";
			}
		} catch (Exception ex) {
			return "Appointments not found";
		}
		return appointmentData;
	}

	/**
	 * GET /getAppointmentByDate --> Return the id for the appointment having the
	 * passed date.
	 */
	@RequestMapping(value = "/getAppointmentByDate", params = "date")
	@ResponseBody
	public String getAppointmentByDate(@RequestParam("date") Date date) {
		String appointmentData = "";
		try {
			Appointment appointment = appointmentDao.findByDate(date);
			appointmentData = appointment.toString();
		} catch (Exception ex) {
			return "Appointment not found";
		}
		return appointmentData;
	}

	// Private fields

	@Autowired
	private UserDao userDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private AppointmentDao appointmentDao;

}
