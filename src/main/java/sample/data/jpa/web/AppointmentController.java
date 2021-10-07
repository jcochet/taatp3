package sample.data.jpa.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;
import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;

//@Controller
@RestController
@RequestMapping("/api")
public class AppointmentController {

	/**
	 * GET /createAppointment --> Create a new appointment and save it in the
	 * database.
	 */
	@RequestMapping("/createAppointment")
	@ResponseBody
	public String createAppointment(Date date, int duration, User user, Worker worker, String description) {
		String appointmentId = "";
		try {
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
	@RequestMapping("/deleteAppointment")
	@ResponseBody
	public String deleteAppointment(long id) {
		try {
			Appointment appointment = new Appointment(id);
			appointmentDao.delete(appointment);
		} catch (Exception ex) {
			return "Error deleting the appointment:" + ex.toString();
		}
		return "Appointment succesfully deleted!";
	}

	/**
	 * GET /getAppointmentByDate --> Return the id for the appointment having the
	 * passed date.
	 */
	@RequestMapping("/getAppointmentByDate/{date}")
	@ResponseBody
	public String getAppointmentByDate(@PathVariable("date") Date date) {
		String appointmentId = "";
		try {
			Appointment appointment = appointmentDao.findByDate(date);
			appointmentId = String.valueOf(appointment.getId());
		} catch (Exception ex) {
			return "Appointment not found";
		}
		return "The appointment id is: " + appointmentId;
	}

	/**
	 * GET /updateAppointment --> Update the attributes for the appointment in the
	 * database having the passed id.
	 */
	@RequestMapping("/updateAppointment")
	@ResponseBody
	public String updateAppointment(long id, Date date, int duration, User user, Worker worker, String description) {
		try {
			Appointment appointment = appointmentDao.findById(id).get();
			appointment.setDate(date);
			appointment.setDuration(duration);
			appointment.setUser(user);
			appointment.setWorker(worker);
			appointment.setDescription(description);
			appointmentDao.save(appointment);
		} catch (Exception ex) {
			return "Error updating the appointment: " + ex.toString();
		}
		return "Appointment succesfully updated!";
	}

	// Private fields

	@Autowired
	private AppointmentDao appointmentDao;
}
