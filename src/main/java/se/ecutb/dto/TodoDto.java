package se.ecutb.dto;

import java.time.LocalDate;
import java.util.Objects;

public class TodoDto {
    private int todoId;
    private String taskDescription;
    private LocalDate deadLine;
    private Integer assigneeId;
    private boolean done;

    public TodoDto(int todoId, String taskDescription, LocalDate deadLine, Integer assigneeId, boolean done) {
        this.todoId = todoId;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.assigneeId = assigneeId;
        this.done = done;
    }

    public int getTodoId() {
        return todoId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDto todoDto = (TodoDto) o;
        return todoId == todoDto.todoId &&
                done == todoDto.done &&
                Objects.equals(taskDescription, todoDto.taskDescription) &&
                Objects.equals(deadLine, todoDto.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoId, taskDescription, deadLine, done);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TodoDto{");
        sb.append("todoId=").append(todoId);
        sb.append(", taskDescription='").append(taskDescription).append('\'');
        sb.append(", deadLine=").append(deadLine);
        sb.append(", assigneeId=").append(assigneeId);
        sb.append(", done=").append(done);
        sb.append('}');
        return sb.toString();
    }
}
