package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Create an activity.
 * @author Tianxin Jia
 *
 */
public abstract class Activity implements Conflict {

    /** Course's title. */
    private String title;
    /** Course's meeting days */
    private String meetingDays;
    /** Course's starting time */
    private int startTime;
    /** Course's ending time */
    private int endTime;

    /**
     * To construct an activity
     * @param title the title of activity
     * @param meetingDays the meeting days of activity
     * @param startTime the start time of activity
     * @param endTime the end time of activity
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        setTitle(title);
        setMeetingDays(meetingDays);
        setActivityTime(startTime, endTime);
    }
    
    /**
     * Get an array to show an activity briefly
     * @return the array to show an activity briefly
     */
    public abstract String[] getShortDisplayArray();
    
    /**
     * Get an longer array to show an activity's detail
     * @return the array to show an activity
     */
    public abstract String[] getLongDisplayArray();
    
    /**
     * To check if two activities are same
     * @param activity the activity to be checked
     * @return true if they are the same, false otherwise
     */
    public abstract boolean isDuplicate(Activity activity); 
    
    /**
     * Returns the Activity's title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the Course's title.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        if (title.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    /**
     * Returns the Activity's meeting Days
     * 
     * @return the meetingDays
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * set the Activity's meeting Days
     * 
     * @param meetingDays the meetingDays to set
     */
    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
        
    }

    /**
     * Returns the Activity's start time
     * 
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the Activity's end time
     * 
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Set the Activity's time
     * @param startTime when the course starts
     * @param endTime when the course ends
     */
    public void setActivityTime(int startTime, int endTime) {
        if (startTime > 2359 || startTime < 0 || endTime > 2359 || endTime < 0) {
            throw new IllegalArgumentException();
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException();
        }
        int st4 = startTime / 10;
        int st3 = st4 % 10;
        int et4 = endTime / 10;
        int et3 = et4 % 10;
        if (et3 > 5 || st3 > 5) {
            throw new IllegalArgumentException();
        }
        String mtd = getMeetingDays();
        if (mtd.equals("a") && (startTime != 0 || endTime != 0)) {
            throw new IllegalArgumentException();
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * providing meeting information as a string in standard time format
     * 
     * @return meeting the meeting information in standard time format
     */
    public String getMeetingString() {
        String meeting;
        if (getMeetingDays().equalsIgnoreCase("A")) {
            meeting = "Arranged";
        } else {
            int st = getStartTime();
            int ed = getEndTime();
            int sth = st / 100;
            int edh = ed / 100;
            String sh;
            String eh;
            if (sth > 12) {
                sth = sth - 12;
                sh = "PM";
            } else if (sth == 12){
                sh = "PM";
            } else {
                sh = "AM";
            }
            if (edh > 12) {
                edh = edh - 12;
                eh = "PM";
            } else if (edh == 12){
                eh = "PM";
            } else {
                eh = "AM";
            }
            String stmi;
            String edmi;
            int stm = st % 100;
            if (stm == 0) {
                stmi = "00";
            } else if (0 < stm && stm < 10) {
                stmi = "0" + stm;
            } else {
                stmi = "" + stm;
            }
            int edm = ed % 100;
            if (edm == 0) {
                edmi = "00";
            } else if (0 < edm && edm < 10) {
                edmi = "0" + edm;
            } else {
                edmi = "" + edm;
            }
            meeting = getMeetingDays() + " " + sth + ":" + stmi + sh + "-" + edh + ":" + edmi + eh;
        }
        return meeting;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endTime;
        result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
        result = prime * result + startTime;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Activity other = (Activity) obj;
        if (endTime != other.endTime)
            return false;
        if (meetingDays == null) {
            if (other.meetingDays != null)
                return false;
        } else if (!meetingDays.equals(other.meetingDays))
            return false;
        if (startTime != other.startTime)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
        String a1 = getMeetingDays();
        String a2 = possibleConflictingActivity.getMeetingDays();
        if(!a1.equals("A") && !a2.equals("A")) {
            int st = getStartTime();
            int et = getEndTime();
            int pst = possibleConflictingActivity.getStartTime();
            int pet = possibleConflictingActivity.getEndTime();
            for(int i = 0; i < a1.length(); i++) {
                Character day = a1.charAt(i);
                for(int j = 0; j < a2.length(); j++) {
                    if(day.equals(a2.charAt(j))){
                        if((st <= pet && st >= pst) || (et <= pet && et >= pst)) {
                            throw new ConflictException();
                        }
                        if((pst >= st && pst <= et) || (pet >= st && pet <= et)) {
                            throw new ConflictException();
                        }
                    }
                }
            }    
        }
    }
}