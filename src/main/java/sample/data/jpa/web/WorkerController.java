package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.WorkerDao;

@RestController
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
	 * GET /getUsers --> Return all users.
	 */
	@RequestMapping("/getWorkers")
	@ResponseBody
	public String getWorkers() {
		String workersData = "";
		try {
			List<Worker> workers = workerDao.findAll();
			for (Worker worker : workers) {
				workersData += worker.toString() + "\r\n";
			}
		} catch (Exception ex) {
			return "Workers not found";
		}
		return workersData;
	}

	/**
	 * GET /getWorkerByEmail --> Return the id for the worker having the passed
	 * email.
	 */
	@RequestMapping(value = "/getWorkerByEmail", params = "email")
	@ResponseBody
	public String getWorkerByEmail(@RequestParam("email") String email) {
		String workersData = "";
		try {
			Worker worker = workerDao.findByEmail(email);
			workersData = worker.toString();
		} catch (Exception ex) {
			return "Worker not found";
		}
		return workersData;
	}

	/**
	 * GET /getWorkerByName --> Return the id for the worker having the passed name.
	 */
	@RequestMapping(value = "/getWorkerByName", params = "name")
	@ResponseBody
	public String getWorkerByName(@RequestParam("name") String name) {
		String workersData = "";
		try {
			Worker worker = workerDao.findByName(name);
			workersData = worker.toString();
		} catch (Exception ex) {
			return "Worker not found";
		}
		return workersData;
	}

	/**
	 * GET /getWorkerByJob --> Return the id for the worker having the passed job.
	 */
	@RequestMapping(value = "/getWorkerByJob", params = "job")
	@ResponseBody
	public String getWorkerByJob(@RequestParam("job") String job) {
		String workersData = "";
		try {
			Worker worker = workerDao.findByJob(job);
			workersData = worker.toString();
		} catch (Exception ex) {
			return "Worker not found";
		}
		return workersData;
	}

	// Private fields

	@Autowired
	private WorkerDao workerDao;

}