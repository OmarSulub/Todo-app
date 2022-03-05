package se.ecutb.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.PersonData;
import se.ecutb.TodoData;
import se.ecutb.config.AppConfig;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = AppConfig.class)
public class TodoRepositoryTest {

    @Autowired private TodoRepository testObject;

    @BeforeEach
    void setUp() {
        testObject.clear();
        List<Todo> data = new TodoData().todoData();
        PersonData personData = new PersonData();
        data.get(0).setAssignee(personData.personData().get(0)); //Assigned to Nisse Nys id: 1
        data.get(0).setDone(true);
        data.get(1).setAssignee(personData.personData().get(1)); //Assigned to Putte Pys id: 2
        data.get(2).setAssignee(personData.personData().get(2)); //Assigned to Olle Olsson id: 3

        data.forEach(testObject::persist);
    }

    @Test
    public void findAll_return_list_of_4_todo(){
        assertEquals(4, testObject.findAll().size());
    }

    @Test
    public void given_clean_findByTaskDescriptionContains_return_list_size_2(){
        String query = "clean";
        List<Todo> result = testObject.findByTaskDescriptionContains(query);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void given_1_findById_return_optional_with_todo(){
        Optional<Todo> optional = testObject.findById(1);
        assertTrue(optional.isPresent());
    }

    @Test
    public void given_2020_03_01_findByDeadlineBefore_should_return_list_of_size_2(){
        LocalDate end = LocalDate.parse("2020-03-01");
        List<Todo> result = testObject.findByDeadLineBefore(end);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void given_2020_03_11_findByDeadLineAfter_should_return_list_of_size_1(){
        LocalDate start = LocalDate.parse("2020-03-11");
        List<Todo> result = testObject.findByDeadLineAfter(start);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void given_2020_02_10_as_start_and_2020_04_01_as_end_findByDeadLineBetween_should_return_list_size_1(){
        LocalDate start = LocalDate.parse("2020-02-10");
        LocalDate end = LocalDate.parse("2020-04-01");
        List<Todo> result = testObject.findByDeadLineBetween(start,end);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void given_1_findByAssigneeId_should_return_list_of_size_1(){
        List<Todo> result = testObject.findByAssigneeId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void findAllUnassignedTasks_should_return_list_size_1(){
        assertEquals(1, testObject.findAllUnassignedTasks().size());
    }

    @Test
    public void findAllAssignedTasks_should_return_list_size_3(){
        assertEquals(3, testObject.findAllAssignedTasks().size());
    }

    @Test
    public void given_true_findByDone_should_return_list_size_1(){
        assertEquals(1, testObject.findByDone(true).size());
    }

    @Test
    public void given_2_delete_should_return_true(){
        assertTrue(testObject.delete(2));
        assertFalse(testObject.findById(2).isPresent());
    }

    @Test
    public void given_7_delete_should_throw_illegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                ()->testObject.delete(7));
    }
}
