package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import models.Job;
import models.JobController;
import models.ParkManager;
import models.Job.WorkCatagories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
    
    ParkManager manager;
    
    JobController controller;
    
    Job job;
    
    Map<String, WorkCatagories> volunteers;
    
    @Before
    public void setUp() throws Exception {
         controller = new JobController();
         manager = new ParkManager("manager@gmail.com", "John", "Iam", "manager");
         volunteers = new HashMap<String, WorkCatagories>();
         job = new Job("manager@gmail.com", "Disneyland Resort"
, "picking up trash", "3/25/15", 12, 0, 3, 0, 5, 0, 7, volunteers);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParkManager() {
        assertEquals("the manager's email", "manager@gmail.com", manager.getEmail());
        assertEquals("the manager's first name", "John", manager.getFirstName());
        assertEquals("the manager's last name", "Iam", manager.getLastName());
        assertEquals("the manager's role", "manager", manager.getRole());
        
    }

    @Test
    public void testRetrieveManagedParks() {
        
        assertEquals(manager.getManagedParks(), manager.retrieveManagedParks("/testFile.csv"));
    }
    @Test
    public void testAddJob() {
        
        assertEquals(manager.addJob(controller, "Disneyland Resort", "picking up trash", "3/25/15", 12, 3, 5, 7), controller.addJob(job));
    }

    @Test
    public void testGetMyJobs() {
        assertEquals(manager.getMyJobs(controller), manager.getManagedJobs());
    }

}
