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

    /**
     * Tests that {@code toString} returns the correct message
     * when there is one task in the list.
     */
    @Test
    void testToString_oneTodoTask_correctStringReturned() {
        TaskList list = new TaskList();
        list.addTask(new TodoTaskStub());
        assertEquals("1.[T][X] ip", list.toString());
    }
}
