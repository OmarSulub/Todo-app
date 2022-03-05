package se.ecutb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.TodoData;
import se.ecutb.config.AppConfig;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Address;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class PersonServiceTest {

    @Autowired private IdSequencers sequencers;
    @Autowired private PersonService testObject;
    @Autowired private TodoRepository todoRepository;
    @Autowired private PersonRepository personRepository;
    private Person testPerson;


    /**
     * Todo{todoId=1, taskDescription='Street cleaning', deadLine=2020-07-11, assignee=null, done=false}
     * Todo{todoId=2, taskDescription='Clean room', deadLine=2020-02-10, assignee=null, done=false}
     * Todo{todoId=3, taskDescription='Repair car', deadLine=2020-03-11, assignee=null, done=false}
     * Todo{todoId=4, taskDescription='Send in report', deadLine=2020-02-05, assignee=null, done=false}
     */
    private List<Todo> todoList;

    @BeforeEach
    void setUp() {
        sequencers.clearTodoId();
        sequencers.clearPersonId();
        todoRepository.clear();
        personRepository.clear();

        TodoData todoData = new TodoData();
        List<Todo> todos = todoData.todoData();

        todos.forEach(todoRepository::persist);

        testPerson = testObject.createPerson("Test", "Testsson","test@gmail.com", new Address("TestStreet 1", "12345", "TestCity"));
        Person p1 = testObject.createPerson("Test2", "Testsson", "test2@gmail.com", new Address("TestStreet 1", "12345", "TestCity"));
        Person lazy1 = testObject.createPerson("Test3", "Testsson", "test3@gmail.com", null);
        Person lazy2 = testObject.createPerson("Test4", "Testsson", "test4@gmail.com", null);
        Person lazy3 = testObject.createPerson("Test5","Olsson", "testOlsson@hotmail.com", null);
        Person p6 = testObject.createPerson("Test6", "Testsson", "test5@gmail.com", new Address("TDD avenue 34A", "23425", "TestCity"));

        todos.get(0).setAssignee(testPerson);
        todos.get(1).setAssignee(testPerson);
        todos.get(2).setAssignee(p1);
        todos.get(3).setAssignee(p6);
        todoList = todos;
    }

    @Test
    public void findAll_return_list_of_size_6(){
        assertEquals(6, testObject.findAll().size());
    }

    @Test
    public void createPerson_with_duplicate_email_return_null(){
        String firstName = "Nils", lastName = "Svensson", email = "test@gmail.com";

        assertNull(testObject.createPerson(firstName,lastName,email,null));
    }

    @Test
    public void given_email_findByEmail_return_Person(){
        String email = "test@gmail.com";

        assertEquals(testPerson, testObject.findByEmail(email));
    }

    @Test
    public void findPeopleWithAssignedTodos_return_list_size_3(){
        List<PersonDtoWithTodo> result = testObject.findPeopleWithAssignedTodos();

        assertEquals(3, result.size());
    }

    @Test
    public void findPeopleWithNoTodos_return_list_size_3(){
        List<PersonDto> result = testObject.findAllPeopleWithNoTodos();

        assertEquals(3, result.size());
    }

    @Test
    public void given_address_findByAddress_return_list_of_2(){
        Address address =  new Address("TestStreet 1", "12345", "TestCity");
        assertEquals(2, testObject.findPeopleByAddress(address).size());
    }

    @Test
    public void given_null_findByAddress_return_list_of_3(){
        assertEquals(3, testObject.findPeopleByAddress(null).size());
    }

    @Test
    public void given_city_findPeopleByCity_return_list_size_3(){
        String city = "TestCity";
        assertEquals(3, testObject.findPeopleByCity(city).size());
    }

    @Test
    public void given_fullName_findPeopleByFullName_return_list_size_1(){
        String fullName = "test testsson";
        assertEquals(1, testObject.findByFullName(fullName).size());
    }

    @Test
    public void given_lastName_findByLastName_return_list_size_5(){
        String lastName = "testsson";
        assertEquals(5, testObject.findByLastName(lastName).size());
    }

    @Test
    public void given_personId_deletePerson_return_true_and_all_references_removed(){
        int personId = testPerson.getPersonId();
        assertTrue(testObject.deletePerson(personId));
        assertFalse(personRepository.findById(personId).isPresent());
        assertTrue(todoRepository.findAllAssignedTasks().stream()
        .noneMatch(todo -> todo.getAssignee().getPersonId() == personId));
    }
}
