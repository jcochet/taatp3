package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.UserDao;
import sample.data.jpa.service.WorkerDao;

import java.util.List;

@Controller
public class WorkerController  {

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name, String password, String job, List<Appointment> ap) {
        String userId = "";
        try {
            Worker w = new Worker(email, name, password,  job,  ap);
            workerDao.save(w);
            userId = String.valueOf(w.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            Worker worker = new Worker(id);
            workerDao.delete(worker);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-email/{email}")
    @ResponseBody
    public String getByEmail(@PathVariable("email") String email) {
        String userId = "";
        try {
            Worker user = workerDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
    /**
     * GET /get-by-name  --> Return the id for the user having the passed
     * name.
     */
    @RequestMapping("/get-by-name/{name}")
    @ResponseBody
    public String findByName(@PathVariable("name") String name) {
        String userId = "";
        try {
            Worker user = workerDao.findByName(name);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
    /**
     * GET /get-by-job  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-job/{job}")
    @ResponseBody
    public String findByJob(@PathVariable("job") String job) {
        String userId = "";
        try {
            Worker user = workerDao.findByJob(job);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            Worker worker = workerDao.findById(id).get();
            worker.setEmail(email);
            worker.setName(name);
            workerDao.save(worker);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // Private fields

    @Autowired
    private WorkerDao workerDao;

}