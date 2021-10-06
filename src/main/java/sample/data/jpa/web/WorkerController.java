package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.WorkerDao;

@Controller
public class WorkerController {

	/**
	 * GET /createWorker --> Create a new worker and save it in the database.
	 */
	@RequestMapping(value = "/createWorker", params = { "email", "name", "password", "job" })
	@ResponseBody
	public String createWorker(@RequestParam("email") String email, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("job") String job) {
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
	 * GET /deleteWorker --> Delete the worker having the passed id.
	 */
	@RequestMapping(value = "/deleteWorker", params = "id")
	@ResponseBody
	public String deleteWorker(@RequestParam("id") Long id) {
		try {
			workerDao.deleteById(id);
		} catch (Exception ex) {
			return "Error deleting the worker:" + ex.toString();
		}
		return "Worker succesfully deleted!";
	}

	/**
	 * GET /getWorkerByEmail --> Return the id for the worker having the passed
	 * email.
	 */
	@RequestMapping("/getWorkerByEmail/{email}")
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
	 * GET /getWorkerByName --> Return the id for the worker having the passed name.
	 */
	@RequestMapping("/getWorkerByName/{name}")
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
	 * GET /getWorkerByJob --> Return the id for the worker having the passed job.
	 */
	@RequestMapping("/getWorkerByJob/{job}")
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

	// Private fields

	@Autowired
	private WorkerDao workerDao;

}