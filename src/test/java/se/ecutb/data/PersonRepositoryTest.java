package se.ecutb.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.PersonData;
import se.ecutb.config.AppConfig;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = AppConfig.class)
public class PersonRepositoryTest extends AbstractPersonFactory {

    @Autowired private PersonRepository testObject;
    private PersonData seeder;

    @BeforeEach
    void setUp() {
        seeder = new PersonData();
        testObject.clear();
        seeder.personData().forEach(testObject::persist);
    }

    @Test
    void findAll_return_data(){
        assertEquals(seeder.personData(), testObject.findAll());
    }

    @Test
    public void given_1_findById_should_return_optional_with_person(){
        Optional<Person> optional = testObject.findById(1);
        assertTrue(optional.isPresent());
    }

    @Test
    public void given_email_findByEmail_should_return_optional_with_person(){
        String email = "putte@gmail.com";
        Optional<Person> optional = testObject.findByEmail(email);

        assertTrue(optional.isPresent());
    }

    @Test
    public void given_person_with_duplicate_email_persist_should_throw_IllegalArgumentException(){
        String duplicateEmail = "nisse@gmail.com";
        Person person = createNewPerson(4, "Nils", "Svensson", duplicateEmail, null);
        assertThrows(IllegalArgumentException.class,
                () -> testObject.persist(person));
    }

    @Test
    public void given_address_findByAddress_should_return_list_of_2_people(){
        Address address = new Address("Teststreet 1", "13434", "TestCity");
        List<Person> result = testObject.findByAddress(address);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void given_null_findByAddress_should_return_list_of_1_person(){
        List<Person> result = testObject.findByAddress(null);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void given_cityName_findByCity_should_return_list_of_2_people(){
        String cityName = "TestCity";
        List<Person> result = testObject.findByCity(cityName);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void given_nys_findByLastName_should_return_list_of_1_person(){
        String lastName = "nys";
        List<Person> result = testObject.findByLastName(lastName);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void given_fullName_findByFullName_should_return_list_of_1_person(){
        String fullName = "nisse nys";
        List<Person> result = testObject.findByFullName(fullName);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void given_1_delete_should_return_true(){
        assertTrue(testObject.delete(1));
        assertFalse(testObject.findById(1).isPresent());
    }

    @Test
    public void given_4_delete_should_throw_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                () -> testObject.delete(4));
    }
}
