package edu.kit.informatik;

/**
 * This class represents the roles people can have.
 * 
 * @author uogok
 * @version 1.0
 */
public enum Role {
    /**
     * One of the roles a person can have.
     */
    STUDENT,

    /**
     * Another role a person can have. Only people with this role can host events.
     */
    LECTURER,

    /**
     * Another role a person can have. For every 5 students there has to be one
     * security.
     */
    SECURITY

}
