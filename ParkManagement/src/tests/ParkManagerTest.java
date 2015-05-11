package tests;

import static org.junit.Assert.*;
import models.ParkManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {

    @Before
    public void setUp() throws Exception {
        
        ParkManager manager = new ParkManager("manager@gmail.com", "John", "Iam", "manager");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParkManager() {
        fail("Not yet implemented");
    }

    @Test
    public void testRetrieveManagedParks() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddJob() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMyJobs() {
        fail("Not yet implemented");
    }

}
