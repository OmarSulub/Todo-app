package se.ecutb.model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {
    private int todoId;
    private String taskDescription;
    private LocalDate deadLine;
    private Person assignee;
    private boolean done;


    Todo(int todoId, String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException{
        if(todoId == 0){
            throw new IllegalArgumentException("Invalid argument: todoId was " + todoId);
        }
        this.todoId = todoId;
        setTaskDescription(taskDescription);
        setDeadLine(deadLine);
        setAssignee(assignee);
        setDone(false);
    }

    public Todo(int todoId, String taskDescription, LocalDate deadLine) {
        this(todoId, taskDescription,deadLine,null);
    }

    Todo(){}

    public int getTodoId() {
        return todoId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        if(taskDescription == null){
            throw new IllegalArgumentException("Invalid argument: taskDescription was " +null);
        }
        this.taskDescription = taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if(deadLine == null){
            throw new IllegalArgumentException("Invalid argument: deadLine was " +null);
        }
        this.deadLine = deadLine;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return todoId == todo.todoId &&
                done == todo.done &&
                Objects.equals(taskDescription, todo.taskDescription) &&
                Objects.equals(deadLine, todo.deadLine) &&
                Objects.equals(assignee, todo.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoId, taskDescription, deadLine, assignee, done);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Todo{");
        sb.append("todoId=").append(todoId);
        sb.append(", taskDescription='").append(taskDescription).append('\'');
        sb.append(", deadLine=").append(deadLine);
        sb.append(", assignee=").append(assignee);
        sb.append(", done=").append(done);
        sb.append('}');
        return sb.toString();
    }
}
