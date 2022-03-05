package se.ecutb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.TodoData;
import se.ecutb.config.AppConfig;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class PersonDtoConversionServiceTest extends AbstractPersonFactory {

    @Autowired private PersonDtoConversionService service;

    /**
     * Todo{todoId=1, taskDescription='Street cleaning', deadLine=2020-07-11, assignee=null, done=false}
     * Todo{todoId=2, taskDescription='Clean room', deadLine=2020-02-10, assignee=null, done=false}
     * Todo{todoId=3, taskDescription='Repair car', deadLine=2020-03-11, assignee=null, done=false}
     * Todo{todoId=4, taskDescription='Send in report', deadLine=2020-02-05, assignee=null, done=false}
     */
    private List<Todo> unassignedTodos;

    private Person personWithTodos;
    private Person personWithoutTodos;

    @BeforeEach
    void setUp() {
        TodoData data = new TodoData();
        unassignedTodos = data.todoData();
    }

    @Test
    public void given_source_convertToPersonDto_return_expected(){
        Person source = createNewPerson(1, "Test", "Testsson", "test@gmail.com", null);
        PersonDto expected = new PersonDto(1,"Test", "Testsson");

        PersonDto actual = service.convertToPersonDto(source);
        assertEquals(expected,actual);
    }

    @Test
    public void given_source_convertToPersonDtoWithTodos_return_successfully_and_contains_correct_data(){
        Person source = createNewPerson(1, "Test", "Testsson", "test@gmail.com", null);
        //Assigning source to all unassignedTodos
        List<Todo> assignedTodos = unassignedTodos.stream().map(todo -> {todo.setAssignee(source); return todo;})
                .collect(Collectors.toList());

        PersonDtoWithTodo result = service.convertToPersonDtoWithTodo(source, assignedTodos);

        assertNotNull(result);
        assertEquals(1, result.getPersonId());
        assertEquals("Test", result.getFirstName());
        assertEquals("Testsson", result.getLastName());
        assertEquals(4, result.getAssignedTodo().size());
        assertTrue(result.getAssignedTodo().stream().allMatch(todoDto -> todoDto.getAssigneeId() == 1));
    }


}
