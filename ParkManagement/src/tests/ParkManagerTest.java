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
    
    int maxJob = 30;
    
    int maxJobNotFull = 10;
    
    int maxJobIsEmpty = 0;
    
    Job job;
    
    Map<String, WorkCategories> volunteers = new HashMap<String, WorkCategories>();
    
    List<String> managedParks;
    
    BusinessRules rules;
    
    
    
    @Before
    public void setUp() throws Exception {
         
//        job = new Job("manager@gmail.com", "Disneyland Resort"
//                      ,"picking up trash", "05/08/2015 09:30 AM","05/08/2015 04:30 PM", 0, 3, 0, 5, 0, 7, null);
        
//        controllerJobFull = new JobController("jobFile1.csv");
//            for(int i = 0; i < maxJob; i++) {
//            
//                controllerJobFull.addJob(job);
//            }
//            
//        controllerJobNotFull = new JobController("jobFile1.csv");
//            for(int i = 0; i < maxJobNotFull; i++) {
//                
//                controllerJobNotFull.addJob(job);
//            }
            
         controllerJobIsEmpty = new JobController("jobFile1.csv");
         controllerJobIsEmpty.loadJobData("jobFile1.csv");
         controllerJobIsEmpty.writeJobData("jobFile1.csv");
         rules = new BusinessRules();
          
         testManager1 = new ParkManager("Testmanager1@gmail.com", "Iam", "John", "manager");
         
         testManager2 = new ParkManager("Testmanager2@gmail.com", "Wang", "Lily", "manager");
         
         testManager3 = new ParkManager("Testmanager3@gmail.com", "Li", "Anna", "manager");
         
         volunteers.put("janedoe@gmail.com", WorkCategories.LIGHT);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMyJobs() {
        
        assertEquals("start with empty list of job", 0, testManager1.getMyJobs(controllerJobIsEmpty).size());
        testManager1.addJob(controllerJobIsEmpty, "Green Lake", "mow the lawn", "05/11/2015 10:00 AM", "05/11/2015 12:00 PM", 3, 3, 5 );
        assertEquals("start with empty list of job", 1, testManager1.getMyJobs(controllerJobIsEmpty).size());
    }

}
