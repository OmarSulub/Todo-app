package se.ecutb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.PersonData;
import se.ecutb.config.AppConfig;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(AppConfig.class)
public class TodoDtoConversionServiceTest extends AbstractTodoFactory {

    @Autowired private TodoDtoConversionService service;
    private Todo todoNoAssignee;
    private Todo todoWithAssignee;

    @BeforeEach
    void setUp() {
        PersonData personData = new PersonData();
        Person assignee = personData.personData().get(0); //Nisse Nys has id of 1
        todoNoAssignee = createTodoItem(1,"TestTodo", LocalDate.parse("2020-02-04"));
        todoWithAssignee = createTodoItem(2,"TestTodo2",LocalDate.parse("2020-02-04"));
        todoWithAssignee.setAssignee(assignee);
    }

    @Test
    public void given_todoNoAssignee_convertToDto_return_expected(){
        TodoDto expected = new TodoDto(1,"TestTodo", LocalDate.parse("2020-02-04"), null, false);
        TodoDto actual = this.service.convertToDto(todoNoAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void given_todoWithAssignee_convertToDto_return_expected(){
        TodoDto expected = new TodoDto(2, "TestTodo2", LocalDate.parse("2020-02-04"), 1, false);
        TodoDto actual = service.convertToDto(todoWithAssignee);
        assertEquals(expected, actual);
    }

    
}
