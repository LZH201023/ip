package duck.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void testToString_noTask_emptyList() {
        TaskList list = new TaskList();
        assertEquals("***EMPTY LIST***", list.toString());
    }
}