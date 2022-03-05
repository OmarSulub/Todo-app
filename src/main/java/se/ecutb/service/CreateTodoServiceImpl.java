package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

@Component
public class CreateTodoServiceImpl extends AbstractTodoFactory implements CreateTodoService {

    private IdSequencers sequencers;

    @Autowired
    public void setSequencers(IdSequencers sequencers) {
        this.sequencers = sequencers;
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException {
        Todo todo = createTodo(taskDescription, deadLine);
        todo.setAssignee(assignee);
        return todo;
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine) throws IllegalArgumentException {
        if(taskDescription == null || deadLine == null){
            throw new IllegalArgumentException();
        }
        return createTodoItem(sequencers.nextTodoId(), taskDescription, deadLine);
    }
}