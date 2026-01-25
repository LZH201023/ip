import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

public class Duck {
    private static final TaskList tasks = new TaskList();
    private static final String HLINE = "-".repeat(50);
    private static final String DUCK = " ____        ____ _   __\n" +
            "|  _ \\ _   _|  __| | / /\n" +
            "| | | | | | | |  | /  /\n" +
            "| |_| | |_| | |__| |\\ \\\n" +
            "|____/ \\__,_|____|_| \\_\\";


    private static void checkMemory() throws IOException {
        File f = new File("./data/duck.txt");
        boolean isCreated = f.createNewFile();
        if (!isCreated) { // file already exists
            try {
                tasks.loadFrom(f);
            } catch (DuckException e) {
                FileWriter fw = new FileWriter(f);
                fw.write("");
                tasks.clearAll();
                System.out.println("Memory corrupted, task list cleared");
            }
        }
    }

    private static boolean isBye(String s) {
        if (s.length() != 3) {
            return false;
        }
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return b1 && b2 && b3;
    }

    private static void parseAndDelete(String s) throws IOException, DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (!s.isEmpty() && idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please delete a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            Task temp = tasks.popTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Noted. I've removed this task:\n" +
                    temp + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }

        // Update memory
        tasks.writeToDefaultFile();
    }

    private static void parseAndMark(String s) throws IOException, DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please mark a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            tasks.markTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Nice! I've marked this task as done:\n" +
                    tasks.getTask(num - 1) + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }

        // Update memory
        tasks.writeToDefaultFile();
    }

    private static void parseAndUnmark(String s) throws IOException, DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please unmark a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            tasks.unmarkTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Ok, I've marked this task as not done yet:\n" +
                    tasks.getTask(num - 1) + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }

        // Update memory
        tasks.writeToDefaultFile();
    }

    private static void parseAndAdd(String s) throws IOException, DuckException {
        String task;
        if (s.startsWith("todo")) {
            task = s.substring(4);
            if (task.isBlank()) {
                throw new DuckException("Missing todo description!");
            } else {
                tasks.addTask(new TodoTask(task.strip()));
            }
        } else if (s.startsWith("deadline")) {
            String[] parts = s.substring(8).concat(" ").split("/");
            if (parts.length < 2) {
                throw new DuckException("Missing deadline!");
            } else if (parts.length > 2) {
                throw new DuckException("Wrong command format.");
            } else if (parts[0].isBlank()){
                throw new DuckException("Missing deadline description!");
            } else if (!parts[1].startsWith("by")) {
                throw new DuckException("Wrong command format.");
            } else if (parts[1].substring(2).isBlank()){
                throw new DuckException("Did you forget to specify deadline?");
            } else {
                parts[0] = parts[0].strip();
                parts[1] = parts[1].substring(2).strip();
                try {
                    LocalDate ddl = LocalDate.parse(parts[1]);
                    DeadlineTask deadline = new DeadlineTask(parts[0], ddl);
                    tasks.addTask(deadline);
                } catch (DateTimeParseException e) {
                    throw new DuckException("Wrong date format:\n" + e.getMessage());
                }
            }
        } else if (s.startsWith("event")) {
            String[] parts = s.substring(5).concat(" ").split("/");
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
                    EventTask event = new EventTask(parts[0], parts[1], parts[2]);
                    tasks.addTask(event);
                } catch (DateTimeParseException e) {
                    throw new DuckException("Wrong date format:\n" + e.getMessage());
                }
            }
        } else {
            throw new DuckException("Sorry I can't understand...");
        }

        System.out.println(HLINE + "\n" + "Got it. I've added this task:\n" +
                tasks.getTask(tasks.getLength() - 1) +
                "\nNow you have " + tasks.getLength() + " tasks in the list\n" + HLINE);

        // Update memory
        tasks.writeToDefaultFile();
    }

    private enum COMMAND {LIST, MARK, UNMARK, DELETE, BYE, ADD};  //To be used in the future if required

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            checkMemory();
        } catch (IOException e) {
            System.out.println("Memory setup unsuccessful, chatbot closing, bye!");
        }

        System.out.println(HLINE + "\nHello! I'm\n" + DUCK +
                "\nWhat can I do for you?\n(btw, plz specify time in yyyy-mm-dd)\n" + HLINE);

        while (true) {
            String next = sc.nextLine();
            String command = next.strip();
            if (command.equals("list")) {
                System.out.println(HLINE + "\n" +
                        "Here are the tasks in your list:\n" + tasks);
                System.out.println(HLINE);
            } else if (command.startsWith("mark")) {
                try {
                    parseAndMark(command.substring(4).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                } catch (IOException e) {
                    System.out.println("Memory update failure, your data could be lost\n" + HLINE);
                }
            } else if (command.startsWith("unmark")) {
                try {
                    parseAndUnmark(command.substring(6).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                } catch (IOException e) {
                    System.out.println("Memory update failure, your data could be lost\n" + HLINE);
                }
            } else if (command.startsWith("delete")) {
                try {
                    parseAndDelete(command.substring(6).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                } catch (IOException e) {
                    System.out.println("Memory update failure, your data could be lost\n" + HLINE);
                }
            } else if (isBye(command)) {
                System.out.println(HLINE + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        HLINE);
                break;
            } else { // Add task
                try {
                    parseAndAdd(command);
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                } catch (IOException e) {
                    System.out.println("Memory update failure, your data could be lost\n" + HLINE);
                }
            }
        }
    }
}
