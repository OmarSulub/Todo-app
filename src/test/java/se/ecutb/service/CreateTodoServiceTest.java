package se.ecutb.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.config.AppConfig;
import se.ecutb.data.IdSequencers;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class CreateTodoServiceTest extends AbstractPersonFactory {

    @Autowired
    CreateTodoService testObject;
    @Autowired
    IdSequencers idSequencers;

    @AfterEach
    void tearDown() {
        idSequencers.clearTodoId();
    }

    @Test
    public void given_taskDescription_and_deadLine_createTodo_should_return_Todo(){
        String taskDescription = "description";
        LocalDate deadLine = LocalDate.now().plusMonths(1);

        Todo todo = testObject.createTodo(taskDescription, deadLine);
        assertNotNull(todo);
        assertEquals(1, todo.getTodoId());
        assertEquals(taskDescription, todo.getTaskDescription());
        assertEquals(deadLine, todo.getDeadLine());
        assertNull(todo.getAssignee());
    }

    @Test
    public void given_taskDescription_deadLine_and_assignee_should_return_Todo(){
        String taskDescription = "description";
        LocalDate deadLine = LocalDate.now().plusMonths(1);
        Person assignee = createNewPerson(1, "Test", "Testsson", "test@test.com", null);

        Todo todo = testObject.createTodo(taskDescription,deadLine,assignee);

        assertNotNull(todo);
        assertEquals(1, todo.getTodoId());
        assertEquals(taskDescription, todo.getTaskDescription());
        assertEquals(deadLine, todo.getDeadLine());
        assertEquals(assignee, todo.getAssignee());
    }

    @Test
    public void given_null_taskDescription_should_throw_IllegalArgumentException(){
        String taskDescription = null;
        LocalDate deadLine = LocalDate.now().plusMonths(1);

        assertThrows(IllegalArgumentException.class,
                () -> testObject.createTodo(taskDescription,deadLine));
    }

    @Test
    public void given_null_deadLine_should_throw_IllegalArgumentException(){
        String taskDescription = "description";
        LocalDate deadLine = null;
        Person assignee = createNewPerson(1, "Test", "Testsson", "test@test.com", null);

        assertThrows(IllegalArgumentException.class,
                () -> testObject.createTodo(taskDescription,deadLine, assignee));
    }
}
