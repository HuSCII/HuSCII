
package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import models.Job.WorkCategories;

/**
 * 
 * @author Ian McPeek
 *
 */
public class JobController implements Serializable {

    /* stores a copy of all jobs in the system. */
    private ArrayList<Job> allJobs;

    /**
     * Creates a new instance of a JobController by loading from given filename.
     */
    public JobController(String filename) {
        allJobs = new ArrayList<Job>();
        if(filename!=null) {
            readJobCereal(filename);
            // Uncomment the following two lines, and comment the above to reload
            // loadJobData("/jobFileFinal.csv");
            // writeJobCereal("src/jobs.huscii");
        } 
    }

    // *****PUBLIC METHODS*****//

    /**
     * Adds a job to the list.
     * 
     * @param job job to be added to allJobs.
     */
    public void addJob(Job job) {
        allJobs.add(job);
    }

    /**
     * Returns a list of all upcoming jobs.
     * 
     * @return upcoming jobs.
     */
    public List<Job> getUpcomingJobs() {
        List<Job> upcoming = new ArrayList<Job>();
        for (int i = 0; i < allJobs.size(); i++) {
            if (!BusinessRules.isCompleted(allJobs.get(i).getEndDate())) {
                // add copy of Job
                upcoming.add(new Job(allJobs.get(i)));
            }
        }
        return upcoming;
    }

    /**
     * Returns a list of all jobs.
     * 
     * @return all jobs.
     */
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<Job>();
        for (Job job : allJobs) {
            jobs.add(new Job(job));
        }
        return jobs;
    }

    /**
     * Returns a String representation of all jobs.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Job job : allJobs) {
            sb.append(job.toString() + "\n");
        }
        return sb.toString();
    }

    // *****FILE I/O METHODS*****//

    /**
     * Loads in a list of jobs from file.
     * 
     * @param filename location of file to read from.
     * @return whether data was successfully loaded or not.
     */
    public boolean loadJobData(String filename) {
        InputStream in = this.getClass().getResourceAsStream(filename);
        // check if filename is valid
        if (in == null) {
            return false;
        }
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            StringTokenizer token = new StringTokenizer(scanner.nextLine(), ",");

            String parkManagerEmail = token.nextToken();
            String parkName = token.nextToken();
            String jobName = token.nextToken();
            String startDate = token.nextToken();
            String endDate = token.nextToken();
            // int duration = Integer.parseInt(token.nextToken());
            int currentLight = Integer.parseInt(token.nextToken());
            int maxLight = Integer.parseInt(token.nextToken());
            int currentMedium = Integer.parseInt(token.nextToken());
            int maxMedium = Integer.parseInt(token.nextToken());
            int currentHeavy = Integer.parseInt(token.nextToken());
            int maxHeavy = Integer.parseInt(token.nextToken());
            Map<String, WorkCategories> signedVolunteers =
                            new HashMap<String, WorkCategories>();
            // collect information
            int i = 0;
            while (token.hasMoreTokens()) {
                // loop for emails
                if (i >= currentLight) {
                    if (i >= currentLight + currentMedium) {
                        signedVolunteers.put(token.nextToken(), WorkCategories.HEAVY);
                    }
                    else {
                        signedVolunteers.put(token.nextToken(), WorkCategories.MEDIUM);
                    }
                }
                else {
                    signedVolunteers.put(token.nextToken(), WorkCategories.LIGHT);
                }
                i++;
            }
            // create job
            Job job =
                            new Job(parkManagerEmail, parkName, jobName, startDate, endDate,
                                    currentLight, maxLight, currentMedium, maxMedium,
                                    currentHeavy, maxHeavy, signedVolunteers);
            // add job
            addJob(job);
        }
        scanner.close();
        try {
            in.close();
        }
        catch (IOException e) {
        }
        return true;
    }

    /**
     * Writes current job data for all jobs to file.
     * 
     * @param filename file location of file to write to.
     * @return whether writing file was a success or not.
     */
    public boolean writeJobData(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(toString());
            writer.close();
        }
        catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean readJobCereal(String filename) {
        FileInputStream fin;
        try {
            fin = new FileInputStream(filename);
            ObjectInputStream oin = new ObjectInputStream(fin);
            allJobs = (ArrayList<Job>) oin.readObject();
            oin.close();
            fin.close();
            return true;
        }
        catch (IOException | ClassNotFoundException e) {
            return false;
        }

    }

    public boolean writeJobCereal(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            // for(Job job:allJobs) {
            oout.writeObject(allJobs);
            // }
            oout.close();
            fout.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
