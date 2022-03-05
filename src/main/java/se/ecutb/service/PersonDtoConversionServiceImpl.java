package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDtoConversionServiceImpl implements PersonDtoConversionService {

    private TodoDtoConversionService todoDtoConversionService;

    @Autowired
    public void setTodoDtoConversionService(TodoDtoConversionService todoDtoConversionService) {
        this.todoDtoConversionService = todoDtoConversionService;
    }

    @Override
    public PersonDto convertToPersonDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName());
    }

    @Override
    public PersonDtoWithTodo convertToPersonDtoWithTodo(Person person, List<Todo> assignedTodos) {
        List<TodoDto> todoDtos = assignedTodos.stream()
                .map(todoDtoConversionService::convertToDto)
                .collect(Collectors.toList());

        PersonDtoWithTodo dto = new PersonDtoWithTodo(
                person.getPersonId(),
                person.getFirstName(),
                person.getLastName(),
                todoDtos
        );
        return dto;
    }
}
