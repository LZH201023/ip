package duck;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duck.command.*;
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
        if (num <= 0) {
            throw new DuckException("Task index out of bound.");
        }
        return num;
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

    private static AddCommand parseTodoTask(String command) throws DuckException {
        String description = command.substring(4);
        if (description.isBlank()) {
            throw new DuckException("Missing todo description!");
        }

        return new AddCommand(new TodoTask(description.strip()));
    }

    private static AddCommand parseDeadlineTask(String command) throws DuckException {
        String[] parts = command.substring(8).concat(" ").split("/");
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
        }

        parts[0] = parts[0].strip();
        parts[1] = parts[1].substring(2).strip();
        try {
            LocalDate ddl = LocalDate.parse(parts[1]);
            return new AddCommand(new DeadlineTask(parts[0], ddl));
        } catch (DateTimeParseException e) {
            throw new DuckException("Wrong date format:\n" + e.getMessage());
        }
    }

    private static AddCommand parseEventTask(String command) throws DuckException {
        String[] parts = command.substring(5).concat(" ").split("/");
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
        }

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

    private static TagCommand parseTagCommand(String command) throws DuckException {
        String body = command.substring(3).strip();
        int l = body.indexOf(" ");
        if (l == -1) {
            throw new DuckException("Missing task index!");
        }
        int num = parseIndex(body.substring(0, l));
        String message = body.substring(l).strip();
        if (message.isEmpty()) {
            throw new DuckException("Missing tag information!");
        }

        return new TagCommand(num, message);
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
        String command = nextInput.strip();
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            int index = parseIndex(command.substring(4).stripLeading());
            assert index > 0 : "Invalid index";
            return new MarkCommand(index);
        } else if (command.startsWith("unmark")) {
            int index = parseIndex(command.substring(6).stripLeading());
            assert index > 0 : "Invalid index";
            return new UnmarkCommand(index);
        } else if (command.startsWith("delete")) {
            int index = parseIndex(command.substring(6).stripLeading());
            assert index > 0 : "Invalid index";
            return new DeleteCommand(index);
        } else if (isBye(command)) {
            return new ExitCommand();
        } else if (command.startsWith("todo")) {
            return parseTodoTask(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineTask(command);
        } else if (command.startsWith("event")) {
            return parseEventTask(command);
        } else if (command.startsWith("find")) {
            String keyword = command.substring(4).strip();
            return new FindCommand(keyword);
        } else if (command.startsWith("tag")) {
            return parseTagCommand(command);
        } else if (command.startsWith("untag")) {
            int index = parseIndex(command.substring(5).stripLeading());
            assert index > 0 : "Invalid index";
            return new UntagCommand(index);
        } else {
            // Command cannot be identified
            throw new DuckException("Sorry I can't understand...");
        }
    }

}
