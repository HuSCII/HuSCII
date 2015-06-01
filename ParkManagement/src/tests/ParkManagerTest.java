package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import models.JobController;
import models.ParkManager;

import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
    
    ParkManager testManager1;
    JobController controllerJobIsEmpty;
    
    @Before
    public void setUp() throws Exception {
         controllerJobIsEmpty = new JobController("src/jobTestFile.csv");
         testManager1 = new ParkManager("walderfrey@gmail.com", "Iam", "John", "manager");
    }

    @Test
    public void testGetMyJobs() {
        assertTrue(testManager1.getMyJobs(controllerJobIsEmpty).isEmpty());
        testManager1.addJob(controllerJobIsEmpty, "Green Lake", "mow the lawn", "07/7/2015 10:00 AM", "07/7/2015 12:00 PM", 3, 3, 5 );
        assertFalse(testManager1.getMyJobs(controllerJobIsEmpty).isEmpty());
    }

}
