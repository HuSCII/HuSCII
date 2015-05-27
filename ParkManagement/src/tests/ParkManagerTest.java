package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Job;
import models.JobController;
import models.ParkManager;
import models.Job.WorkCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
    
    ParkManager manager;
    
    JobController controller;
    
    Job job;
    
    Map<String, WorkCategories> volunteers = new HashMap<String, WorkCategories>();
    
    List<Job> managedJobs;
    
    List<String> managedParks;
    
    
    
    @Before
    public void setUp() throws Exception {
         
         controller = new JobController();
         manager = new ParkManager("manager@gmail.com", "Iam", "John", "manager");
         volunteers.put("janedoe@gmail.com", WorkCategories.LIGHT);
         job = new Job("manager@gmail.com", "Disneyland Resort"
                       ,"picking up trash", "05/08/2015 09:30 AM","05/08/2015 04:30 PM", 0, 3, 0, 5, 0, 7, null);
         manager.addJob()
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParkManager() {
        assertEquals("the manager's email", "manager@gmail.com", manager.getEmail());
        assertEquals("the manager's last name", "Iam", manager.getLastName());
        assertEquals("the manager's last name", "John", manager.getFirstName());
        assertEquals("the manager's role", "manager", manager.getRole());
        
    }

    
    public void testAddJob() {
        
        assertEquals(manager.addJob(controller, "Disneyland Resort", "picking up trash", 
                                    "05/08/2016 09:30 AM", "05/08/2015 04:30 PM",3, 5, 7), 
                                    controller.addJob(job));
    }

    @Test
    public void testGetMyJobs() {
        assertEquals(manager.getMyJobs(controller), manager.getManagedJobs());
    }

}
