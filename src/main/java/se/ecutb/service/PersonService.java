package se.ecutb.service;

import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(String firstName, String lastName, String email, Address address);
    List<PersonDto> findAll();

    /**
     *
     * @param personId int
     * @return PersonDto of found Person
     * @throws IllegalArgumentException should be thrown when Person is not found
     */
    PersonDto findById(int personId) throws IllegalArgumentException;

    /**
     *
     * @param email String
     * @return found Person
     * @throws IllegalArgumentException should be thrown when Person is not found
     */
    Person findByEmail(String email) throws IllegalArgumentException;
    List<PersonDtoWithTodo> findPeopleWithAssignedTodos();
    List<PersonDto> findAllPeopleWithNoTodos();
    List<PersonDto> findPeopleByAddress(Address address);
    List<PersonDto> findPeopleByCity(String city);
    List<PersonDto> findByFullName(String fullName);
    List<PersonDto> findByLastName(String lastName);

    /**
     * Should delete <b>all references</b> to person object with matching personId
     * @param personId int
     * @return true if removed
     * @throws IllegalArgumentException when Person is not found
     */
    boolean deletePerson(int personId) throws IllegalArgumentException;
}
