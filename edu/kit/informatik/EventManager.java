package edu.kit.informatik;

import java.util.Scanner;

/**
 * This class represents the main class of the project which is the event
 * manager. This class manages all the methods and controls given into the
 * system.
 * 
 * @author uogok
 * @version 3.6
 *
 */
public class EventManager {

    /**
     * Represents the maximum number of people allowed.
     */
    private final int maxNumberOfPeople = 1000;

    /**
     * Represents maximum number of events allowed.
     */
    private final int maxNumberOfEvents = 100;

    /**
     * due to magic number error.
     */
    private final int twoWeeks = 14;

    /**
     * due to magic number error.
     */
    private final int maxDate = 364;

    /**
     * Represents the main date. It is also the current date.
     */
    private Date mainDate = new Date();

    /**
     * Represents the list of all the people added.
     */
    private LinkedList<Person> listOfPeople = new LinkedList<Person>();

    /**
     * Represents the list of all the events that have been added.
     */
    private LinkedList<Event> listOfEvents = new LinkedList<Event>();

    /**
     * This is the main method. The scanner scans the input and calls the required
     * methods here.
     * 
     * @param args
     */
    public static void main(String[] args) {

        EventManager em = new EventManager();
        
        em.setDateMain(0);
        em.addPerson("student", "Infected", "Person");
        em.addPerson("student", "Suhas", "sjrgiudrbkg");
        em.addPerson("student", "someone", "idk");
        em.addPerson("student", "fourth", "person");
        em.addPerson("lecturer", "prof1", "surname1");
        em.addPerson("lecturer", "prof2", "surname2");
        em.addPerson("security", "police", "man");
        em.addPerson("security", "police", "woman");
        em.addCertificate(0, "vaccinated", 10);
        em.addCertificate(1, "vaccinated", 10);
        em.addCertificate(2, "vaccinated", 10);
        em.addCertificate(3, "vaccinated", 10);
        em.addCertificate(4, "vaccinated", 10);
        em.addCertificate(5, "vaccinated", 10);
        em.addCertificate(6, "vaccinated", 10);
        em.addCertificate(7, "vaccinated", 10);

        em.addEvent(4, "Audimax1", 10, "3G ", 42);
        em.addEvent(5, "Audimax2", 10, "3G ", 42);
        em.addEvent(4, "Audimax3", 10, "3G ", 42);
        em.addEvent(5, "Audimax4", 10, "3G ", 42);

        em.increaseSecurity(0, 6);
        em.increaseSecurity(1, 7);
        em.increaseSecurity(2, 6);
        em.increaseSecurity(3, 7);

        em.bookSpot(0, 0);
        em.bookSpot(1, 0);
        em.bookSpot(2, 0);
        em.bookSpot(0, 1);
        em.bookSpot(1, 1);
        em.bookSpot(2, 1);
        em.bookSpot(0, 0);
        System.out.println("Below are the: ");
        em.reportCase(4);

        Scanner sc = new Scanner(System.in);
        String sentence = null;

        while ((sentence = sc.nextLine()) != null) {

            String[] words = sentence.split(" ");
            String[] parameters = null;
            if (words.length != 1) {
                parameters = words[1].split(";");
            }
            switch (words[0]) {
                case "set-date":
                    em.setDateMain(Integer.valueOf(words[1]));
                    break;
                case "add-person":
                    em.addPerson(parameters[0], parameters[1], parameters[2]);
                    break;
                case "add-certificate":
                    em.addCertificate(Integer.valueOf(parameters[0]), parameters[1], Integer.valueOf(parameters[2]));
                    break;
                case "print-person":
                    em.printPerson(Integer.valueOf(words[1]));
                    break;
                case "print-people":
                    em.printPeople(words[1]);
                    break;
                case "add-event":
                    em.addEvent(Integer.valueOf(parameters[0]), parameters[1], Integer.valueOf(parameters[2]),
                            parameters[3], Integer.valueOf(parameters[3 + 1])); // 3 + 1 cause 4 is a magic number.
                    break;
                case "increase-security":
                    em.increaseSecurity(Integer.valueOf(parameters[0]), Integer.valueOf(parameters[1]));
                    break;
                case "book-spot":
                    em.bookSpot(Integer.valueOf(parameters[0]), Integer.valueOf(parameters[1]));
                    break;
                case "report-case":
                    em.reportCase(Integer.valueOf(parameters[0]));
                    break;
                case "quit":
                    sc.close();
                    sc = null;
                    return;
                default:
                    System.out.println("Syntax error");
                    break;
            }

        }
    }

    private void printPersonWithJointEvents(Person personToPrint) {
        String details = personToPrint.getiD() + ", " + personToPrint.getFirstName() + " " + personToPrint.getLastName()
                + ", " + personToPrint.getRole().toString().toLowerCase() + ", "
                + covidProofType(personToPrint, mainDate) + " [" + personToPrint.getNumberOFJointEvents() + "]";

        System.out.println(details);

    }

    private boolean doesPersonExist(int personID) {

        if (personID >= 0 && personID <= listOfPeople.getSize() - 1) {
            return listOfPeople.contains(listOfPeople.getObjectWithID(personID).getContent());
        }
        return false;
    }

    private boolean checkStudentToSecurityRatio(int eventID) {
        int numberOfStudents = listOfEvents.getObjectWithID(eventID).getContent().getStudents().getSize();
        int numberOfSecurity = listOfEvents.getObjectWithID(eventID).getContent().getSecurity().getSize();
        if (numberOfSecurity * 5 >= numberOfStudents) {
            return true;
        }
        return false;
    }

    private boolean validDate(int date) {
        return date <= maxDate && date >= 0;
    }

    private Role checkRole(String role) {

        Role r = null;

        if (role.equals("security")) {

            r = Role.SECURITY;

        } else if (role.equals("lecturer")) {

            r = Role.LECTURER;

        } else {
            r = Role.STUDENT;
        }
        return r;
    }

    private boolean eventHasPerson(int eventID, int personID) {
        if (listOfPeople.getObjectWithID(personID).getContent().getRole().toString().toLowerCase().equals("student")) {
            return listOfEvents.getObjectWithID(eventID).getContent().getStudents()
                    .contains(listOfPeople.getObjectWithID(personID).getContent());
        }
        return listOfEvents.getObjectWithID(eventID).getContent().getSecurity()
                .contains(listOfPeople.getObjectWithID(personID).getContent());
    }

    private String covidProofType(Person random, Date date) {
        if (random.getCovidProof() == null) {
            return "no certificate";
        } else if (random.getCovidProof().isVaccinated()) {
            return "vaccinated";
        } else if (random.getCovidProof().isRecovered()
                && random.getCovidProof().getExpiryDateRecovered().getDate() >= date.getDate()) {
            return "recovered";
        } else if (random.getCovidProof().isTested()
                && random.getCovidProof().getExpiryDateTest().getDate() >= date.getDate()) {
            random.getCovidProof().setRecovered(false);
            return "tested";
        } else {
            random.getCovidProof().setTested(false);
            random.getCovidProof().setRecovered(false);
            return "no certificate";
        }
    }

    private boolean checkPersonsCovidProof(int eventID, int personID) {
        Date dateOfEvent = listOfEvents.getObjectWithID(eventID).getContent().getDateOfEvent();

        if ((covidProofType(listOfPeople.getObjectWithID(personID).getContent(), dateOfEvent)
                .equals("no certificate"))) {
            return false;
        } else if ((covidProofType(listOfPeople.getObjectWithID(personID).getContent(), dateOfEvent)
                .equals("tested"))) {
            if (listOfEvents.getObjectWithID(eventID).getContent().is2G()) {
                return false;
            }
        }
        return true;

    }

    /**
     * Sets the main date.
     * 
     * @param newDate The main date is to be set to this value.
     */
    public void setDateMain(int newDate) {

        if (validDate(newDate)) {
            mainDate.setDate(newDate);
            System.out.println("OK");

        } else {
            System.out.println("Invalid Date. Date must be between 0 and 364");
        }
    }

    /**
     * Adds the person into the list of people.
     * 
     * @param role      represents the role that every person must have.
     * @param firstName represents the first name that every person must have.
     * @param lastName  represents the last name that every person must have.
     */
    public void addPerson(String role, String firstName, String lastName) {
        if (listOfPeople.getSize() <= maxNumberOfPeople) {

            Role r = checkRole(role);

            int iD = listOfPeople.getSize();
            listOfPeople.push(new Person(r, iD, firstName, lastName));

            System.out.println(listOfPeople.traverseToLastCell().getContent().getiD());
        } else {
            System.out.println("Maximum number of people reached");
        }
    }

    /**
     * Adds the covid-proof certificate to a certain person.
     * 
     * @param personID The certificate is to be added to the person with this ID.
     * @param proof    Represents type of covid-proof, it can have the values:
     *                 tested, vaccinated or recovered/
     * @param newDate  Represents the date of the certificate.
     */
    public void addCertificate(int personID, String proof, int newDate) {
        if (doesPersonExist(personID) && validDate(newDate)) {

            CovidProof covidProof = new CovidProof();
            Date date = new Date();
            date.setDate(newDate);

            if (proof.equals("tested")) {
                covidProof.setTested(true, date);

            } else if (proof.equals("recovered")) {

                covidProof.setRecovered(true, date);

            } else {

                covidProof.setVaccinated(true, date);
            }

            listOfPeople.getObjectWithID(personID).getContent().setCovidProof(covidProof);
            System.out.println("OK");
        }
    }

    /**
     * Prints the certain person.
     * 
     * @param personID prints the person with this ID.
     */
    public void printPerson(int personID) {
        if (doesPersonExist(personID)) {
            Person newPerson = listOfPeople.getObjectWithID(personID).getContent();

            String details = newPerson.getiD() + ", " + newPerson.getFirstName() + " " + newPerson.getLastName() + ", "
                    + newPerson.getRole().toString().toLowerCase() + ", " + covidProofType(newPerson, mainDate);

            System.out.println(details);
        }

    }

    /**
     * Prints all the people with a certain role.
     * 
     * @param role Prints all the people who has this role, roles can have the
     *             value: security, lecturer or student.
     */
    public void printPeople(String role) {
        Role r = checkRole(role);
        boolean hasMatches = false;
        for (int i = 0; i < listOfPeople.getSize(); i++) {
            if (listOfPeople.getObjectWithID(i).getContent().getRole() == r) {
                hasMatches = true;
                printPerson(i);
            }
        }
        if (!hasMatches) {
            System.out.println("No " + role + " in the system");
        }
    }

    /**
     * Adds an event into the list of events.
     * 
     * @param hostID           represents the ID of the host who hosts this
     *                         particular event.
     * @param lectureRoomName  represents the name of the building, where the event
     *                         is to be held.
     * @param capacity         represents the maximum number of people allowed in
     *                         this event.
     * @param restrictionLevel represents the covid-regulations restriction level,
     *                         it can have the value 2G or 3G.
     * @param newDate          represents the date, when the event is to be held.
     */
    public void addEvent(int hostID, String lectureRoomName, int capacity, String restrictionLevel, int newDate) {
        if (listOfEvents.getSize() <= maxNumberOfEvents) {

            if (listOfPeople.getObjectWithID(hostID).getContent().getRole() == Role.LECTURER) {
                boolean highRestriction = false;
                Date date = new Date();
                date.setDate(newDate);
                if (restrictionLevel.equals("2G")) {
                    highRestriction = true;
                }

                listOfEvents.push(new Event(listOfEvents.getSize(), listOfPeople.getObjectWithID(hostID).getContent(),
                        lectureRoomName, capacity, highRestriction, date));

                System.out.println(listOfEvents.traverseToLastCell().getContent().getiD());
            } else {
                System.out.println("ID does not belong to a lecturer");
            }
        } else {
            System.out.println("Maximum number of events reached");
        }
    }

    /**
     * Adds security to a particular event. As long as the capacity is not full,
     * this security isn't already participating in this event.
     * 
     * @param eventID
     * @param securityID
     */
    public void increaseSecurity(int eventID, int securityID) {
        if (doesPersonExist(securityID) && eventID <= listOfEvents.getSize() - 1) {
            boolean capacity = listOfEvents.getObjectWithID(eventID).getContent().isThereCapacity();
            boolean contains = eventHasPerson(eventID, securityID);
            boolean obeysCovidRegulations = checkPersonsCovidProof(eventID, securityID);
            if (capacity && !contains && obeysCovidRegulations) {

                listOfEvents.getObjectWithID(eventID).getContent()
                        .setSecurity(listOfPeople.getObjectWithID(securityID).getContent());
                System.out.println("OK");

            } else {
                System.out.println("Could not add security");

            }
        } else {
            System.out.println("Could not add security");
        }
    }

    /**
     * The book-spot command enables students to book a place for an event. All
     * students can only take part in the same event once.
     * 
     * @param eventID   The person, mostly students are to be booked to the event
     *                  with this event ID.
     * @param studentID The person with this ID is to be added to the event with
     *                  eventID.
     */
    public void bookSpot(int eventID, int studentID) {
        if (doesPersonExist(studentID) && eventID <= listOfEvents.getSize() - 1) {
            boolean capacity = listOfEvents.getObjectWithID(eventID).getContent().isThereCapacity();
            boolean contains = eventHasPerson(eventID, studentID);
            boolean obeysCovidRegulations = checkPersonsCovidProof(eventID, studentID);
            boolean hasEnoughSecurity = checkStudentToSecurityRatio(eventID);

            if (capacity && !contains && obeysCovidRegulations && hasEnoughSecurity) {

                listOfEvents.getObjectWithID(eventID).getContent()
                        .setStudents(listOfPeople.getObjectWithID(studentID).getContent());

                int spotsLeft = listOfEvents.getObjectWithID(eventID).getContent().howManySpotsLeft();
                System.out.println(spotsLeft + " spot(s) left");

            } else {
                System.out.println("Could not book spot");

            }
        } else {
            System.out.println("Could not book spot");
        }
    }

    /**
     * This method reports a positive case and then outputs all people who have been
     * with the positive person in at least one event in the last 14 days. In
     * addition, the number of joint events is given for each contact person.
     * 
     * @param personID the person with this ID is infected.
     */
    public void reportCase(int personID) {

        if (doesPersonExist(personID)) {
            Person infectedPerson = listOfPeople.getObjectWithID(personID).getContent();
            LinkedList<Event> eventsWithInfected = new LinkedList<Event>();
            LinkedList<Person> infectedPeople = new LinkedList<Person>();

            // check in which events the infected person was in and save the events in a new
            // list
            for (int i = 0; i < listOfEvents.getSize(); i++) {
                Event variableEvent = listOfEvents.getObjectWithID(i).getContent();

                if (variableEvent.getDateOfEvent().getDate() + twoWeeks >= mainDate.getDate()) {

                    if (infectedPerson.getRole() == Role.LECTURER && variableEvent.getHost() == infectedPerson) {
                        eventsWithInfected.push(variableEvent);

                    } else if (infectedPerson.getRole() == Role.STUDENT
                            && variableEvent.getStudents().contains(infectedPerson)) {

                        eventsWithInfected.push(variableEvent);

                    } else if (infectedPerson.getRole() == Role.SECURITY
                            && variableEvent.getSecurity().contains(infectedPerson)) {

                        eventsWithInfected.push(variableEvent);

                    }

                }
            }
            // save all the infected people in a new list
            for (int i = 0; i < eventsWithInfected.getSize(); i++) {

                Event variableInfectedEvent = eventsWithInfected.getObjectWithID(i).getContent();

                // add the host
                infectedPeople.push(variableInfectedEvent.getHost());

                // add the security
                for (int j = 0; j < variableInfectedEvent.getSecurity().getSize(); j++) {
                    infectedPeople.push(variableInfectedEvent.getSecurity().getObjectWithID(j).getContent());

                }
                // add the students
                for (int k = 0; k < variableInfectedEvent.getStudents().getSize(); k++) {
                    infectedPeople.push(variableInfectedEvent.getStudents().getObjectWithID(k).getContent());

                }

            }
            infectedPeople.remove(infectedPerson);
            while (infectedPeople.getSize() > 0) {
                int smallestID = findSmallestID(infectedPeople);
                int duplicate = infectedPeople.remove(listOfPeople.getObjectWithID(smallestID).getContent());
                listOfPeople.getObjectWithID(smallestID).getContent().setNumberOFJointEvents(duplicate);
                printPersonWithJointEvents(listOfPeople.getObjectWithID(smallestID).getContent());
            }

        } else {
            System.out.println("Person does not exist");
        }

    }

    private int findSmallestID(LinkedList<Person> listOfInfectedPeople) {

        Person personWithLowID = listOfInfectedPeople.getObjectWithID(0).getContent();

        for (int i = 0; i < listOfInfectedPeople.getSize(); i++) {
            Person variablePerson = listOfInfectedPeople.getObjectWithID(i).getContent();
            if (personWithLowID.getiD() > variablePerson.getiD()) {
                personWithLowID = variablePerson;
            }
        }
        return personWithLowID.getiD();
    }

}
