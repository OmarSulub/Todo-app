package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private CreateTodoService createTodoService;
    private TodoDtoConversionService conversionService;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    public void setCreateTodoService(CreateTodoService createTodoService) {
        this.createTodoService = createTodoService;
    }

    @Autowired
    public void setConversionService(TodoDtoConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) {
        Todo todo = null;
        try{
            if(assignee == null){
                todo = createTodoService.createTodo(taskDescription, deadLine);
            }else{
                todo = createTodoService.createTodo(taskDescription, deadLine, assignee);
            }
            todo = todoRepository.persist(todo);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        return todo;
    }

    @Override
    public TodoDto findById(int todoId) throws IllegalArgumentException {
        Todo todo = todoRepository.findById(todoId).orElseThrow(IllegalArgumentException::new);
        return conversionService.convertToDto(todo);
    }

    @Override
    public List<TodoDto> findByTaskDescription(String taskDescription) {
        return todoRepository.findByTaskDescriptionContains(taskDescription).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findByDeadLineBefore(LocalDate endDate) {
        return todoRepository.findByDeadLineBefore(endDate).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findByDeadLineAfter(LocalDate startDate) {
        return todoRepository.findByDeadLineAfter(startDate).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findByDeadLineBetween(LocalDate startDate, LocalDate endDate) {
        return todoRepository.findByDeadLineBetween(startDate, endDate).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findAssignedTasksByPersonId(int personId) {
        return todoRepository.findByAssigneeId(personId).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findUnassignedTasks() {
        return todoRepository.findAllUnassignedTasks().stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findAssignedTasks() {
        return todoRepository.findAllAssignedTasks().stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findByDoneStatus(boolean done) {
        return todoRepository.findByDone(done).stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(conversionService::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(int todoId) throws IllegalArgumentException {
        return todoRepository.delete(todoId);
    }
}
