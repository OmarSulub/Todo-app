package se.ecutb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.config.AppConfig;
import se.ecutb.data.IdSequencers;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
public class CreatePersonServiceTest {

    @Autowired private CreatePersonService testObject;
    @Autowired private IdSequencers idSequencers;

    @BeforeEach
    void setup(){
        idSequencers.clearTodoId();
        idSequencers.clearPersonId();
    }

    @Test
    public void given_non_null_firstName_lastName_and_email_create_should_return_Person(){
        String firstName = "Test", lastName = "Testsson", email = "test@gmail.com";

        Person person = testObject.create(firstName,lastName,email);

        assertNotNull(person);
        assertEquals(1, person.getPersonId());
        assertEquals("Test", person.getFirstName());
        assertEquals("Testsson", person.getLastName());
        assertEquals("test@gmail.com", person.getEmail());
        assertNull(person.getAddress());
    }

    @Test
    public void given_non_null_firstName_lastName_email_and_address_should_return_Person(){
        String firstName = "Test", lastName = "Testsson", email = "test@gmail.com";
        Address address = new Address("Testgatan 1", "13456", "Test city");

        Person person = testObject.create(firstName,lastName,email,address);

        assertNotNull(person);
        assertEquals(1, person.getPersonId());
        assertEquals("Test", person.getFirstName());
        assertEquals("Testsson", person.getLastName());
        assertEquals("test@gmail.com", person.getEmail());
        assertEquals(address, person.getAddress());
    }

    @Test
    public void given_null_firstName_should_throw_IllegalArgumentException(){
        String firstName = null, lastName = "Testsson", email = "test@gmail.com";
        assertThrows(IllegalArgumentException.class,
                () -> testObject.create(firstName, lastName, email));
    }

    @Test
    public void given_null_lastName_should_throw_IllegalArgumentException(){
        String firstName = "Test", lastName = null, email = "test@gmail.com";
        assertThrows(IllegalArgumentException.class,
                () -> testObject.create(firstName, lastName, email));
    }

    @Test
    public void given_null_email_should_throw_IllegalArgumentException(){
        String firstName = "Test", lastName = "Testsson", email = null;
        assertThrows(IllegalArgumentException.class,
                () -> testObject.create(firstName, lastName, email));
    }
}
