package duck;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

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