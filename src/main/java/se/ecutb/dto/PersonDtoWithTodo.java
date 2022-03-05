package se.ecutb.dto;

import java.util.List;
import java.util.Objects;

public class PersonDtoWithTodo {
    private int personId;
    private String firstName;
    private String lastName;
    private List<TodoDto> assignedTodo;

    public PersonDtoWithTodo(int personId, String firstName, String lastName, List<TodoDto> assignedTodo) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assignedTodo = assignedTodo;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<TodoDto> getAssignedTodo() {
        return assignedTodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDtoWithTodo that = (PersonDtoWithTodo) o;
        return personId == that.personId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(assignedTodo, that.assignedTodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, assignedTodo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDtoWithTodo{");
        sb.append("personId=").append(personId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", assignedTodo=").append(assignedTodo);
        sb.append('}');
        return sb.toString();
    }
}
