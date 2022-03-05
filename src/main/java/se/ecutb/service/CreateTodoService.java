package se.ecutb.service;

import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

public interface CreateTodoService {
    /**
     *
     * @param taskDescription  not null
     * @param deadLine  not null
     * @param assignee nullable
     * @return new Instantiated TodoObject with id
     * @throws IllegalArgumentException when any of taskDescription or deadLine is null
     */
    Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException;

    /**
     *
     * @param taskDescription not null
     * @param deadLine not null
     * @return new Instantiated TodoObject with id
     * @throws IllegalArgumentException when any passed in parameter is null
     */
    Todo createTodo(String taskDescription, LocalDate deadLine) throws IllegalArgumentException;
}
