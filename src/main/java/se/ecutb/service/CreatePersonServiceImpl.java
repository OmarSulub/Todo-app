package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

@Component
public class CreatePersonServiceImpl extends AbstractPersonFactory implements CreatePersonService {

    private IdSequencers sequencers;

    @Autowired
    public void setSequencers(IdSequencers sequencers) {
        this.sequencers = sequencers;
    }

    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {
        if(firstName == null || lastName == null || email == null){
            throw new IllegalArgumentException();
        }
        return createNewPerson(sequencers.nextPersonId(), firstName, lastName, email, null);
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {
        Person person = create(firstName, lastName, email);
        person.setAddress(address);
        return person;
    }
}
