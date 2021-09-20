package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.WorkerDao;

@Controller
public class WorkerController {

	/**
	 * GET /create --> Create a new worker and save it in the database.
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String createWorker(String email, String name, String password, String job) {
		String workerId = "";
		try {
			Worker worker = new Worker(email, name, password, job);
			workerDao.save(worker);
			workerId = String.valueOf(worker.getId());
		} catch (Exception ex) {
			return "Error creating the worker: " + ex.toString();
		}
		return "Worker succesfully created with id = " + workerId;
	}

	/**
	 * GET /delete --> Delete the worker having the passed id.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteWorker(long id) {
		try {
			Worker worker = new Worker(id);
			workerDao.delete(worker);
		} catch (Exception ex) {
			return "Error deleting the worker:" + ex.toString();
		}
		return "Worker succesfully deleted!";
	}

	/**
	 * GET /get-by-email --> Return the id for the worker having the passed email.
	 */
	@RequestMapping("/get-by-email/{email}")
	@ResponseBody
	public String getWorkerByEmail(@PathVariable("email") String email) {
		String workerId = "";
		try {
			Worker worker = workerDao.findByEmail(email);
			workerId = String.valueOf(worker.getId());
		} catch (Exception ex) {
			return "Worker not found";
		}
		return "The worker id is: " + workerId;
	}

	/**
	 * GET /get-by-name --> Return the id for the worker having the passed name.
	 */
	@RequestMapping("/get-by-name/{name}")
	@ResponseBody
	public String getWorkerByName(@PathVariable("name") String name) {
		String workerId = "";
		try {
			Worker worker = workerDao.findByName(name);
			workerId = String.valueOf(worker.getId());
		} catch (Exception ex) {
			return "Worker not found";
		}
		return "The worker id is: " + workerId;
	}

	/**
	 * GET /get-by-job --> Return the id for the worker having the passed job.
	 */
	@RequestMapping("/get-by-job/{job}")
	@ResponseBody
	public String getWorkerByJob(@PathVariable("job") String job) {
		String workerId = "";
		try {
			Worker worker = workerDao.findByJob(job);
			workerId = String.valueOf(worker.getId());
		} catch (Exception ex) {
			return "Worker not found";
		}
		return "The worker id is: " + workerId;
	}

	/**
	 * GET /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateWorker(long id, String email, String name, String password, String job,
			List<Appointment> appointments) {
		try {
			Worker worker = workerDao.findById(id).get();
			worker.setEmail(email);
			worker.setName(name);
			worker.setPassword(password);
			worker.setJob(job);
			worker.setAppointments(appointments);
			workerDao.save(worker);
		} catch (Exception ex) {
			return "Error updating the worker: " + ex.toString();
		}
		return "Worker succesfully updated!";
	}

	// Private fields

	@Autowired
	private WorkerDao workerDao;

}