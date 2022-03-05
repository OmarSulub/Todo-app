package se.ecutb.model;

public abstract class AbstractPersonFactory {
    protected Person createNewPerson(int personId, String firstName, String lastName, String email, Address address) {
        return new Person(personId, firstName, lastName, email, address);
    }
}
