package se.ecutb.service;

import se.ecutb.model.Address;
import se.ecutb.model.Person;

public interface CreatePersonService {
    /**
     *
     * @param firstName not null
     * @param lastName not null
     * @param email not null
     * @return Instantiated Person with id
     * @throws IllegalArgumentException when any passed in parameter is null
     */
    Person create(String firstName, String lastName, String email) throws IllegalArgumentException;

    /**
     *
     * @param firstName not null
     * @param lastName not null
     * @param email not null
     * @param address - nullable
     * @return Instantiated Person with id
     * @throws IllegalArgumentException when any of firstName, lastName or email is null
     */
    Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException;
}
