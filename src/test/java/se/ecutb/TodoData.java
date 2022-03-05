package se.ecutb;

import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TodoData extends AbstractTodoFactory {
    public List<Todo> todoData(){
        return Arrays.asList(
            createTodoItem(1, "Street cleaning", LocalDate.parse("2020-07-11")),
                createTodoItem(2, "Clean room", LocalDate.parse("2020-02-10")),
                createTodoItem(3, "Repair car", LocalDate.parse("2020-03-11")),
                createTodoItem(4, "Send in report", LocalDate.parse("2020-02-05"))
        );
    }


}
