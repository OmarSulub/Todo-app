package se.ecutb.service;

import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee);

    /**
     *
     * @param todoId int
     * @return TodoDto of found TodoItem
     * @throws IllegalArgumentException should be thrown when no TodoItem is found
     */
    TodoDto findById(int todoId) throws IllegalArgumentException;
    List<TodoDto> findByTaskDescription(String taskDescription);
    List<TodoDto> findByDeadLineBefore(LocalDate endDate);
    List<TodoDto> findByDeadLineAfter(LocalDate startDate);
    List<TodoDto> findByDeadLineBetween(LocalDate startDate, LocalDate endDate);
    List<TodoDto> findAssignedTasksByPersonId(int personId);
    List<TodoDto> findUnassignedTasks();
    List<TodoDto> findAssignedTasks();
    List<TodoDto> findByDoneStatus(boolean done);
    List<TodoDto> findAll();

    /**
     *
     * @param todoId int
     * @return true if TodoItem was found and removed
     * @throws IllegalArgumentException thrown when no TodoItem was found
     */
    boolean delete(int todoId) throws IllegalArgumentException;

}
