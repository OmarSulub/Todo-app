package se.ecutb.service;

import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;

public interface PersonDtoConversionService {
    PersonDto convertToPersonDto(Person person);
    PersonDtoWithTodo convertToPersonDtoWithTodo(Person person, List<Todo> assignedTodos);
}
