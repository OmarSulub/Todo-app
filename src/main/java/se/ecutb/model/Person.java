package se.ecutb.model;

import java.util.Objects;

public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;


     Person(int personId, String firstName, String lastName, String email, Address address) {
        this.personId = personId;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddress(address);
    }

    Person(){}

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IllegalArgumentException{
         if(firstName == null){
             throw new IllegalArgumentException("Illegal argument: firstName was " + null);
         }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException{
        if(lastName == null){
            throw new IllegalArgumentException("Illegal argument: lastName was " + null);
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException{
        if(email == null){
            throw new IllegalArgumentException("Illegal argument: email was " + null);
        }
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("personId=").append(personId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
