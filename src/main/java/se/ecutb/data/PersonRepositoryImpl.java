package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> people;

    public PersonRepositoryImpl() {
        people = new ArrayList<>();
    }

    @Override
    public Optional<Person> findById(int personId) {
        return people.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
        if(findByEmail(person.getEmail()).isPresent()){
            throw new IllegalArgumentException();
        }
        if(!people.contains(person)){
            people.add(person);
        }
        return person;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return people.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        if(address != null){
            return people.stream()
                    .filter(person -> person.getAddress() != null)
                    .filter(person -> person.getAddress().equals(address))
                    .collect(Collectors.toList());
        }else{
            return peopleWithNoAddress();
        }
    }

    private List<Person> peopleWithNoAddress() {
        return people.stream()
                .filter(person -> person.getAddress() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByCity(String city) {
        return people.stream()
                .filter(person -> person.getAddress() != null)
                .filter(person -> person.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return people.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        return people.stream()
                .filter(person -> (person.getFirstName() + " " + person.getLastName()).equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return people;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        Optional<Person> optional = findById(personId);
        if(!optional.isPresent()){
            throw new IllegalArgumentException();
        }
        return people.remove(optional.get());
    }

    @Override
    public void clear() {
        people = new ArrayList<>();
    }
}
