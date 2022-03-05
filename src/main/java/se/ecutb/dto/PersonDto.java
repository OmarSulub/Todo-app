package se.ecutb.dto;

import java.util.Objects;

public class PersonDto {
    private int personId;
    private String firstName;
    private String lastName;

    public PersonDto(int personId, String firstName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDto(){}

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return personId == personDto.personId &&
                Objects.equals(firstName, personDto.firstName) &&
                Objects.equals(lastName, personDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDto{");
        sb.append("personId=").append(personId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
