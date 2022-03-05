package se.ecutb.service;

import se.ecutb.dto.TodoDto;
import se.ecutb.model.Todo;

public interface TodoDtoConversionService {
    TodoDto convertToDto(Todo todo);
}
