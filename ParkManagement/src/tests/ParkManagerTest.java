package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import models.Job;
import models.JobController;
import models.ParkManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
    
    ParkManager manager;
    
    JobController controller;
    
    Job job;
    
    @Before
    public void setUp() throws Exception {
         controller = new JobController();
         manager = new ParkManager("manager@gmail.com", "John", "Iam", "manager");
         
        
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
        
        assertEquals(manager.getManagedParks(), manager.retrieveManagedParks("testFile.csv"));
    }
    @Test
    public void testAddJob() {
        
    }

    @Test
    public void testGetMyJobs() {
        assertEquals(manager.getMyJobs(controller), manager.getManagedJobs());
    }

}
