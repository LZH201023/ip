package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for the {@link TaskList} class.
 * This class verifies the correctness of task list behaviors.
 */
class TaskListTest {

    /**
     * Tests that {@code toString} returns the empty list message
     * when the task list contains no tasks.
     */
    @Test
    void testToString_noTask_emptyList() {
        TaskList list = new TaskList();
        assertEquals("***EMPTY LIST***", list.toString());
    }
}
