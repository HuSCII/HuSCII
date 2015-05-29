package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.BusinessRules;
import models.Job;
import models.JobController;
import models.ParkManager;
import models.Job.WorkCategories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
    
    ParkManager testManager1, testManager2, testManager3;

//    JobController controllerJobFull;
//    JobController controllerJobNotFull;
    JobController controllerJobIsEmpty;

    BusinessRules rules;
    
    @Before
    public void setUp() throws Exception {

         controllerJobIsEmpty = new JobController("src/jobFileTest.txt");
         controllerJobIsEmpty.loadJobData("jobFile1.csv");
         controllerJobIsEmpty.writeJobData("jobFile1.csv");
         rules = new BusinessRules();
          
         testManager1 = new ParkManager("walderfrey@gmail.com", "Iam", "John", "manager");
         
         testManager2 = new ParkManager("Testmanager2@gmail.com", "Wang", "Lily", "manager");
         
         testManager3 = new ParkManager("Testmanager3@gmail.com", "Li", "Anna", "manager");
         
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMyJobs() {
        
        
        testManager1.addJob(controllerJobIsEmpty, "Green Lake", "mow the lawn", "05/11/2015 10:00 AM", "05/11/2015 12:00 PM", 3, 3, 5 );
        assertEquals("parkmanager's email matches","walderfrey@gmail.com" , testManager1.getMyJobs(controllerJobIsEmpty).get(0).getParkManagerEmail());
    }

}
