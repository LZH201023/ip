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

}
