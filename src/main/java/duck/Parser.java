package duck;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duck.command.Command;
import duck.command.DeleteCommand;
import duck.command.ListCommand;
import duck.command.MarkCommand;
import duck.command.UnmarkCommand;
import duck.command.ExitCommand;
import duck.command.AddCommand;

import duck.task.DeadlineTask;
import duck.task.EventTask;
import duck.task.TodoTask;

/**
 * Parses user input strings into executable {@link Command} objects.
 * This class validates command formats and throws {@link DuckException} for invalid inputs.
 */
class Parser {

    /**
     * Parses a one-based task index from the given string.
     * This method accepts only strings containing digits and rejects non-positive values.
     *
     * @param s The string containing the task index.
     * @return The parsed one-based task index.
     * @throws DuckException If the input contains non-digit characters or if the index is not positive.
     */
    private static int parseIndex(String s) throws DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (idx > 0 && idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please specify a proper task!");
        }
        if (num > 0) {
            return num;
        } else {
            throw new DuckException("duck.task.Task index out of bound.");
        }
    }

    /**
     * Checks whether the given string represents the exit command.
     * This check is case-insensitive and matches only the word "bye".
     *
     * @param s The input string to be checked.
     * @return {@code true} if the input equals "bye" ignoring case. Otherwise, {@code false}.
     */
    private static boolean isBye(String s) {
        if (s.length() != 3) {
            return false;
        }
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return b1 && b2 && b3;
    }

    /**
     * Parses the given raw user input into a corresponding {@link Command}.
     * This method trims surrounding whitespace and determines the command type based on prefixes and keywords.
     *
     * @param nextInput The raw input string entered by the user.
     * @return A {@link Command} instance representing the user instruction.
     * @throws DuckException If the input does not match any valid command format.
     */
    public static Command parse(String nextInput) throws DuckException {
        String cmd = nextInput.strip();
        if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.startsWith("mark")) {
            int index = parseIndex(cmd.substring(4).stripLeading());
            return new MarkCommand(index);
        } else if (cmd.startsWith("unmark")) {
            int index = parseIndex(cmd.substring(6).stripLeading());
            return new UnmarkCommand(index);
        } else if (cmd.startsWith("delete")) {
            int index = parseIndex(cmd.substring(6).stripLeading());
            return new DeleteCommand(index);
        } else if (isBye(cmd)) {
            return new ExitCommand();
        } else if (cmd.startsWith("todo")) {
            String description = cmd.substring(4);
            if (description.isBlank()) {
                throw new DuckException("Missing todo description!");
            } else {
                return new AddCommand(new TodoTask(description.strip()));
            }
        } else if (cmd.startsWith("deadline")) {
            String[] parts = cmd.substring(8).concat(" ").split("/");
            if (parts.length < 2) {
                throw new DuckException("Missing deadline!");
            } else if (parts.length > 2) {
                throw new DuckException("Wrong command format.");
            } else if (parts[0].isBlank()) {
                throw new DuckException("Missing deadline description!");
            } else if (!parts[1].startsWith("by")) {
                throw new DuckException("Wrong command format.");
            } else if (parts[1].substring(2).isBlank()) {
                throw new DuckException("Did you forget to specify deadline?");
            } else {
                parts[0] = parts[0].strip();
                parts[1] = parts[1].substring(2).strip();
                try {
                    LocalDate ddl = LocalDate.parse(parts[1]);
                    return new AddCommand(new DeadlineTask(parts[0], ddl));
                } catch (DateTimeParseException e) {
                    throw new DuckException("Wrong date format:\n" + e.getMessage());
                }
            }
        } else if (cmd.startsWith("event")) {
            String[] parts = cmd.substring(5).concat(" ").split("/");
            if (parts.length < 2) {
                throw new DuckException("Missing time of event!");
            } else if (parts.length == 2) {
                throw new DuckException("Please specify a proper timing for event.");
            } else if (parts.length > 3) {
                throw new DuckException("Wrong command format.");
            } else if (parts[0].isBlank()) {
                throw new DuckException("Missing event description!");
            } else if (!parts[1].startsWith("from") || !parts[2].startsWith("to")) {
                throw new DuckException("Wrong command format.");
            } else if (parts[1].substring(4).isBlank()) {
                throw new DuckException("Oops, did you forget starting time?");
            } else if (parts[2].substring(2).isBlank()) {
                throw new DuckException("Oops, did you forget ending time?");
            } else {
                parts[0] = parts[0].strip();
                parts[1] = parts[1].substring(4).strip();
                parts[2] = parts[2].substring(2).strip();
                try {
                    LocalDate from = LocalDate.parse(parts[1]);
                    LocalDate to = LocalDate.parse(parts[2]);
                    return new AddCommand(new EventTask(parts[0], from, to));
                } catch (DateTimeParseException e) {
                    throw new DuckException("Wrong date format:\n" + e.getMessage());
                }
            }
        } else {
            // Command cannot be identified
            throw new DuckException("Sorry I can't understand...");
        }
    }

}
