package edu.kit.informatik;

/**
 * This class represents the event.
 * 
 * @author uogok
 * @version 3.6
 *
 */
public class Event {

    private static int numberOfEvents = 0;
    private int iD;
    private Person host;
    private LinkedList<Person> students;
    private LinkedList<Person> security;
    private Date dateOfEvent;
    private String lectureRoomName;
    private int capacity;
    private boolean onlyVaccinatedAndRecovered; // set and is 2G

    /**
     * Is the constructor for this class.
     * 
     * @param eventID                    the unique ID of the event.
     * @param host                       The host who holds the event, is always a
     *                                   lecturer.
     * @param lectureRoomName            Name of the building.
     * @param capacity                   capacity of the event, as in how many
     *                                   people are allowed
     * @param onlyVaccinatedAndRecovered if the value is true then it is 2G,
     *                                   otherwise 3G
     * @param date                       date of the event
     */
    Event(int eventID, Person host, String lectureRoomName, int capacity, boolean onlyVaccinatedAndRecovered,
            Date date) {
        setiD(eventID);
        setHost(host);
        setLectureRoomName(lectureRoomName);
        setCapacity(capacity);
        set2G(onlyVaccinatedAndRecovered);
        setDateOfEvent(date);
        this.students = new LinkedList<Person>();
        this.security = new LinkedList<Person>();
        numberOfEvents++;

    }

    /**
     * Checks if there is still capacity.
     * 
     * @return true, if there are still spots left.
     */
    public boolean isThereCapacity() {
        if (howManySpotsLeft() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Counts how many spots are left;
     * 
     * @return the number of spots
     */
    public int howManySpotsLeft() {
        int numberOfPeople = 1 + students.getSize() + security.getSize();
        return capacity - numberOfPeople;
    }

    /**
     * Get the person who hosts the event
     * 
     * @return the lecturer who holds/hosts the event
     */
    public Person getHost() {
        return this.host;
    }

    /**
     * Adds/sets the person who hosts the event
     * 
     * @param host is the lecturer who holds the event
     */
    public void setHost(Person host) {
        this.host = host;
    }

    /**
     * Gets the list of all the students participating in this event
     * 
     * @return list of all the students participating in this event
     */
    public LinkedList<Person> getStudents() {
        return students;
    }

    /**
     * Add a new student to the list of participating students.
     * 
     * @param newStudent This student is to be added to the list of students
     */
    public void setStudents(Person newStudent) {
        this.students.push(newStudent);
    }

    /**
     * Gets the list of all the security personnel participating in this event.
     * 
     * @return list of all the security staff participating in this event.
     */
    public LinkedList<Person> getSecurity() {
        return security;
    }

    /**
     * Add a new security staff to the list of participating security.
     * 
     * @param newSecurity Is to be added to the list of security.
     */
    public void setSecurity(Person newSecurity) {
        this.security.push(newSecurity);
    }

    /**
     * Gets the date of the event.
     * 
     * @return date of the event.
     */
    public Date getDateOfEvent() {
        return this.dateOfEvent;
    }

    /**
     * 
     * Set the date of the event.
     * 
     * @param date is to be set as the date of the current event.
     */
    public void setDateOfEvent(Date date) {
        this.dateOfEvent = date;
    }

    /**
     * Gets the covid-regulations status of the current event.
     * 
     * @return true, if the events regulations are 2G.
     */
    public boolean is2G() {
        return this.onlyVaccinatedAndRecovered;
    }

    /**
     * Sets the covid-regulations status of the current event.
     * 
     * @param onlyVaccinatedAndRecovered is true if the regualtions are 2G.
     */
    public void set2G(boolean onlyVaccinatedAndRecovered) {
        this.onlyVaccinatedAndRecovered = onlyVaccinatedAndRecovered;
    }

    /**
     * Gets the number of events currently being managed by the event manager.
     * 
     * @return the number of events.
     */
    public static int getNumberOfEvents() {
        return numberOfEvents;
    }

    /**
     * Gets the event's unique ID.
     * 
     * @return the events unique ID.
     */
    public int getiD() {
        return this.iD;
    }

    /**
     * Sets the events ID to the parameter value.
     * 
     * @param iD this is to be set as the event ID.
     */
    public void setiD(int iD) {
        this.iD = iD;
    }

    /**
     * Gets the name of the building the event takes place in.
     * 
     * @return The name of the events building.
     */
    public String getLectureRoomName() {
        return lectureRoomName;
    }

    /**
     * Sets the name of the building at which the event takes place.
     * 
     * @param lectureRoomName the current event is to be held here.
     */
    public void setLectureRoomName(String lectureRoomName) {
        this.lectureRoomName = lectureRoomName;
    }

    /**
     * Gets the capacity of the event, which is the number of people allowed in the
     * event.
     * 
     * @return the capacity of the event.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the capacity of the event, which is the number of people allowed in the
     * event.
     * 
     * @param capacity this number is to be set as the capacity of the event.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
