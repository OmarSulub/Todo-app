package se.ecutb.model;

import java.time.LocalDate;

public abstract class AbstractTodoFactory {
    protected Todo createTodoItem(int todoId, String taskDescription, LocalDate deadLine, Person assignee){
        return new Todo(todoId,taskDescription,deadLine,assignee);
    }

    protected Todo createTodoItem(int todoId, String taskDescription, LocalDate deadLine){
        return new Todo(todoId, taskDescription, deadLine);
    }
}
