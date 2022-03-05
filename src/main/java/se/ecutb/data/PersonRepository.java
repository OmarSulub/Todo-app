package se.ecutb.data;

import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findById(int personId);

    /**
     *
     * @param person
     * @return Person person persisted
     * @throws IllegalArgumentException when duplicate email conflict is detected.
     */
    Person persist(Person person) throws IllegalArgumentException;
    Optional<Person> findByEmail(String email);
    List<Person> findByAddress(Address address);
    List<Person> findByCity(String city);
    List<Person> findByLastName(String lastName);
    List<Person> findByFullName(String fullName);
    List<Person> findAll();

    /**
     *
     * @param personId Unique id of person to remove
     * @return true - person removed
     * @throws IllegalArgumentException when no matching Person for passed in id exists
     */
    boolean delete(int personId) throws IllegalArgumentException;

    /**
     * Clear repository of all Person Objects. Intended for use in testing.
     */
    void clear();
}
