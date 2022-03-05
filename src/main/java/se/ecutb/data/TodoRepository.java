package se.ecutb.data;

import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo persist(Todo todo);
    Optional<Todo> findById(int todoId);
    List<Todo> findByTaskDescriptionContains(String taskDescription);
    List<Todo> findByDeadLineBefore(LocalDate endDate);
    List<Todo> findByDeadLineAfter(LocalDate startDate);
    List<Todo> findByDeadLineBetween(LocalDate start, LocalDate end);
    List<Todo> findByAssigneeId(int personId);
    List<Todo> findAllUnassignedTasks();
    List<Todo> findAllAssignedTasks();
    List<Todo> findByDone(boolean isDone);
    List<Todo> findAll();

    /**
     *
     * @param todoId Unique id for TodoItems
     * @return true if TodoItem was removed
     * @throws IllegalArgumentException when no TodoItem matching passed in id is found
     */
    boolean delete(int todoId) throws IllegalArgumentException;

    /**
     * Remove all TodoItems from the repository. Intended to be used in tests.
     */
    void clear();
}
