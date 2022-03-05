package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Address;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private TodoRepository todoRepository;
    private CreatePersonService createPersonService;
    private PersonDtoConversionService conversionService;



    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    public void setCreatePersonService(CreatePersonService createPersonService) {
        this.createPersonService = createPersonService;
    }

    @Autowired
    public void setConversionService(PersonDtoConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Person createPerson(String firstName, String lastName, String email, Address address) {
        Person person = null;
        try {
            if(email != null){
                if(personRepository.findByEmail(email).isPresent()){
                    throw new IllegalArgumentException("Duplicate email");
                }
            }
            person = createPersonService.create(firstName, lastName, email, address);
            person = personRepository.persist(person);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        return person;
    }

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto findById(int personId) throws IllegalArgumentException {
        Person person = personRepository.findById(personId)
                .orElseThrow(IllegalArgumentException::new);
        return conversionService.convertToPersonDto(person);
    }

    @Override
    public Person findByEmail(String email) throws IllegalArgumentException {
        return personRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<PersonDtoWithTodo> findPeopleWithAssignedTodos() {
        Map<Person, List<Todo>> map = todoRepository.findAllAssignedTasks().stream()
                .collect(Collectors.groupingBy(Todo::getAssignee));

        List<PersonDtoWithTodo> result = new ArrayList<>();

        map.forEach((person, list) ->
                result.add(conversionService.convertToPersonDtoWithTodo(person,list)));
        return result;
    }

    @Override
    public List<PersonDto> findAllPeopleWithNoTodos() {
        List<Person> hasAssignedTodos = todoRepository.findAllAssignedTasks().stream()
                .map(Todo::getAssignee)
                .collect(Collectors.toList());

        return personRepository.findAll().stream()
                .filter(person -> !hasAssignedTodos.contains(person))
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findPeopleByAddress(Address address) {
        return personRepository.findByAddress(address).stream()
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findPeopleByCity(String city) {
        return personRepository.findByCity(city).stream()
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findByFullName(String fullName) {
        return personRepository.findByFullName(fullName).stream()
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName).stream()
                .map(conversionService::convertToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deletePerson(int personId) throws IllegalArgumentException {
        boolean result = personRepository.delete(personId);
        todoRepository.findByAssigneeId(personId).forEach(todo -> todo.setAssignee(null));
        return result;
    }
}