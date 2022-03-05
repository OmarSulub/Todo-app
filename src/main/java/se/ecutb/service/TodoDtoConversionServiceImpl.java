package se.ecutb.service;

import org.springframework.stereotype.Component;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Todo;

@Component
public class TodoDtoConversionServiceImpl implements TodoDtoConversionService {
    @Override
    public TodoDto convertToDto(Todo todo) {
        return new TodoDto(
                todo.getTodoId(),
                todo.getTaskDescription(),
                todo.getDeadLine(),
                todo.getAssignee() == null ? null : todo.getAssignee().getPersonId(),
                todo.isDone()
        );
    }
}