package se.ecutb;

import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.Arrays;
import java.util.List;

public class PersonData extends AbstractPersonFactory {
    public List<Person> personData(){
        return Arrays.asList(
                createNewPerson(1, "Nisse", "Nys", "nisse@gmail.com", new Address("Teststreet 1", "13434", "TestCity")),
                createNewPerson(2, "Putte", "Pys", "putte@gmail.com", new Address("Teststreet 1", "13434", "TestCity")),
                createNewPerson(3, "Olle","Olsson", "ollesson@gmail.com", null)
        );
    }
}
