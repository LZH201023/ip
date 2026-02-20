package duck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for the {@link Parser} class.
 * This class verifies the correctness of command parsing behavior.
 */
class ParserTest {

    /**
     * Tests that an invalid deadline command format
     * throws a {@link DuckException} with the correct error message.
     */
    @Test
    void testParse_wrongInputFormat_throwDuckException() {
        try {
            Parser.parse("deadline Homework \\by 1998-01-02");
            fail();
        } catch (DuckException e) {
            assertEquals("Missing deadline!", e.getMessage());
        }
    }

    /**
     * Tests that an invalid todo command format
     * throws a {@link DuckException} with the correct error message.
     */
    @Test
    void testParse_noDescriptionForTodoTask_throwDuckException() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DuckException e) {
            assertEquals("Missing todo description!", e.getMessage());
        }
    }

    /**
     * Tests that an invalid event command format
     * throws a {@link DuckException} with the correct error message.
     */
    @Test
    void testParse_extraInputArgument_throwDuckException() {
        try {
            Parser.parse("event ip /from 2026-01-15 /from 2020-02-02 /to 2027-01-01");
            fail();
        } catch (DuckException e) {
            assertEquals("Wrong command format.", e.getMessage());
        }
    }

}
