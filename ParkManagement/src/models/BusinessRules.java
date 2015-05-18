package models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BusinessRules {
    
    private static final int MAX_JOBS = 30;
    
    /**
     * Checks if the maximum job limit has been reached.
     * @return whether maximum is met.
     */
    public static boolean checkMaxJobs(List<Job> allJobs) {
        if(allJobs.size()<MAX_JOBS) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks if the week quota(5) has been met for a given week.
     * @return whether week quota is met.
     */
    public static boolean checkJobWeek(List<Job> allJobs, GregorianCalendar date) {
        GregorianCalendar pastDate = new GregorianCalendar(date.getTimeZone());
        GregorianCalendar futureDate = new GregorianCalendar(date.getTimeZone());
        int count = 0;
        pastDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH)-3);
        futureDate.set(Calendar.DAY_OF_MONTH, pastDate.get(Calendar.DAY_OF_MONTH)+3);
        
        for(Job aJob:allJobs) {
            if(aJob.getDate().compareTo(pastDate)>=0 &&
                    date.compareTo(futureDate)<=0) {
                count++;
            }
            if(count>=5) {
                return false;
            }
        }
        return true;
    }

}
