package se.ecutb.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import se.ecutb.config.AppConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = AppConfig.class)
public class IdSequencersTest {

    @Autowired
    private IdSequencers testObject;

    @BeforeEach
    void setUp() {
        testObject.clearPersonId();
        testObject.clearTodoId();
    }

    @Test
    void nextPersonIdIs_1_and_next_todoId_is_1(){
        assertEquals(1, testObject.nextPersonId());
        assertEquals(1, testObject.nextTodoId());
    }
}
