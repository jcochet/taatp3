package sample.data.jpa.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;

@Controller
public class AppointmentController {

	/**
	 * GET /create --> Create a new appointment and save it in the database.
	 */
	@RequestMapping("/create")
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
	 * GET /delete --> Delete the appointment having the passed id.
	 */
	@RequestMapping("/delete")
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
	 * GET /get-by-date --> Return the id for the appointment having the passed
	 * date.
	 */
	@RequestMapping("/get-by-date/{date}")
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
	 * GET /update --> Update the attributes for the appointment in the database
	 * having the passed id.
	 */
	@RequestMapping("/update")
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
