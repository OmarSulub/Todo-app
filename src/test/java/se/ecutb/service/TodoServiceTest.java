package se.ecutb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.PersonData;
import se.ecutb.config.AppConfig;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class TodoServiceTest {
    @Autowired private IdSequencers sequencers;
    @Autowired private TodoService testObject;
    @Autowired private TodoRepository todoRepository;
    private Todo testTodo;

    /**
     * Person{personId=1, firstName='Nisse', lastName='Nys', email='nisse@gmail.com', address=Address{street='Teststreet 1', zipCode='13434', city='TestCity'}}
     * Person{personId=2, firstName='Putte', lastName='Pys', email='putte@gmail.com', address=Address{street='Teststreet 1', zipCode='13434', city='TestCity'}}
     * Person{personId=3, firstName='Olle', lastName='Olsson', email='ollesson@gmail.com', address=null}
     */
    private List<Person> personData;



    @BeforeEach
    void setUp() {
        sequencers.clearPersonId(); sequencers.clearTodoId();
        todoRepository.clear();
        PersonData personData = new PersonData();
        this.personData = personData.personData();
        testTodo = testObject.createTodo("Testdescription", LocalDate.parse("2020-02-28"), this.personData.get(0));
        testObject.createTodo("Testdescription2", LocalDate.parse("2020-02-06"), this.personData.get(0));
        testObject.createTodo("Testdescription3", LocalDate.parse("2020-02-07"), this.personData.get(2));
        testObject.createTodo("Testdescription4", LocalDate.parse("2020-02-08"), this.personData.get(0));
        testObject.createTodo("Testdescription5", LocalDate.parse("2020-02-10"), this.personData.get(1));
        testObject.createTodo("Testdescription6", LocalDate.parse("2020-02-15"), this.personData.get(1));
        testObject.createTodo("Testdescription7", LocalDate.parse("2020-02-11"), null);
    }

    @Test
    public void given_text_findByTaskDescription_return_list_size_7(){
        String text = "test";
        List<TodoDto> result = testObject.findByTaskDescription(text);
        assertEquals(7, result.size());
    }

    @Test
    public void given_date_findByDeadLineBefore_return_list_size_2(){
        LocalDate date = LocalDate.parse("2020-02-08");
        assertEquals(2, testObject.findByDeadLineBefore(date).size());
    }

    @Test
    public void given_date_findByDeadLineAfter_return_list_size_3(){
        LocalDate date = LocalDate.parse("2020-02-10");
        assertEquals(3, testObject.findByDeadLineAfter(date).size());
    }

    @Test
    public void given_start_and_end_findByDeadLineBetween_return_list_size_2(){
        LocalDate start = LocalDate.parse("2020-02-07");
        LocalDate end = LocalDate.parse("2020-02-11");
        assertEquals(2, testObject.findByDeadLineBetween(start,end).size());
    }

    @Test
    public void given_personId_findAssignedTasksByPersonId_return_list_size_3(){
        int personId = personData.get(0).getPersonId();
        assertEquals(3, testObject.findAssignedTasksByPersonId(personId).size());
    }

    @Test
    public void findUnassignedTasks_return_list_size_1(){
        assertEquals(1, testObject.findUnassignedTasks().size());
    }

    @Test
    public void findAssignedTasks_return_list_size_6(){
        assertEquals(6, testObject.findAssignedTasks().size());
    }

    @Test
    public void given_false_findByDoneStatus_return_list_size_7(){
        assertEquals(7, testObject.findByDoneStatus(false).size());
    }

    @Test
    public void given_todoId_findById_should_return_todoDto(){
        TodoDto result = testObject.findById(testTodo.getTodoId());
        assertNotNull(result);
    }

    @Test
    public void given_1000_findById_should_throw_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                () -> testObject.findById(1000));
    }

    @Test
    public void findAllShould_return_list_size_7(){
        assertEquals(7, testObject.findAll().size());
    }

    @Test
    public void given_testId_delete_should_return_true_and_todo_is_removed(){
        assertTrue(testObject.delete(testTodo.getTodoId()));
        assertFalse(todoRepository.findById(testTodo.getTodoId()).isPresent());
    }




}
