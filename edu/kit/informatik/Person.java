package edu.kit.informatik;

/**
 * This class represents a typical person.
 * 
 * @author uogok
 * @version 6.2.3
 *
 */
public class Person {

    private static int count = 0; // never set the count
    private int iD;
    private Role role;
    private String firstName;
    private String lastName;
    private CovidProof covidProof;
    private int numberOfJointEvents;

    /**
     * Represents the constructor for the class Person.
     * 
     * @param role      Every person has a role, which has the value: security,
     *                  lecturer or student.
     * @param iD        every person has a unique ID.
     * @param firstName every person must have a first-name.
     * @param lastName  every person must have a surname.
     */
    Person(Role role, int iD, String firstName, String lastName) {
        setRole(role);
        setiD(iD);
        setFirstName(firstName);
        setLastName(lastName);
        setNumberOFJointEvents(0);
        count++;
    }

    /**
     * Gets the number of people added.
     * 
     * @return the number of people existing/who have been added.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Gets the role of a certain person.
     * 
     * @return role of a person.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets the roll of a person.
     * 
     * @param role contains the value: student, lecturer or security.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the first name of a person.
     * 
     * @return the first name of the person as a String.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the person.
     * 
     * @param firstName is to be set as the first name of the person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of a person.
     * 
     * @return the last name of the person as a String.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of the person.
     * 
     * @param lastName is to be set as the last name of the person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the covid-proof certificate of a person.
     * 
     * @return the covid-proof certificate.
     */
    public CovidProof getCovidProof() {
        return this.covidProof;
    }

    /**
     * Sets the covid-proof certificate of a person.
     * 
     * @param covidProof this is to be set as the person's covid-proof certificate.
     */
    public void setCovidProof(CovidProof covidProof) {
        this.covidProof = covidProof;
    }

    /**
     * Gets the ID of the person.
     * 
     * @return the ID of the person as an int.
     */
    public int getiD() {
        return this.iD;
    }

    /**
     * Sets the ID of the person.
     * 
     * @param iD sets the ID of the person to this value.
     */
    public void setiD(int iD) {
        this.iD = iD;
    }

    /**
     * Gets the number of joint events the person had with the infected person.
     * 
     * @return the number of events the person attended with the infected person.
     */
    public int getNumberOFJointEvents() {
        return this.numberOfJointEvents;
    }

    /**
     * Sets the number of joint events the person had with the infected person.
     * 
     * @param numberOFJointEvents this value is to be set as the number of joint
     *                            events the person had with the infected person.
     */
    public void setNumberOFJointEvents(int numberOFJointEvents) {
        this.numberOfJointEvents = numberOFJointEvents;
    }

}
