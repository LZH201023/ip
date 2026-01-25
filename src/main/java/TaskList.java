import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    public Task getTask(int idx) {
        return this.list.get(idx);
    }

    public int getLength() {
        return this.list.size();
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void markTaskAt(int idx) {
        this.list.get(idx).markAsDone();
    }

    public void unmarkTaskAt(int idx) {
        this.list.get(idx).markAsUndone();
    }

    public Task popTaskAt(int idx) {
        Task temp = this.list.get(idx);
        this.list.remove(idx);
        return temp;
    }

    public void clearAll() {
        this.list.clear();
    }
    public void loadFrom(File f) throws FileNotFoundException, DuckException {
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.length() < 3) {
                throw new DuckException();
            }
            char c1 = next.charAt(0);
            char c2 = next.charAt(1);
            if (!(c2 == '1') && !(c2 == '0')) {
                throw new DuckException();
            }
            boolean isMarked = c2 == '1';

            if (c1 == 'T') {
                String description = next.substring(2).strip();
                if (description.isEmpty()) {
                    throw new DuckException();
                } else {
                    this.addTask(new TodoTask(description));
                }

            } else if (c1 == 'D') {
                String[] texts = next.substring(2).concat(" ").split("/");
                if (texts.length != 2) {
                    throw new DuckException();
                } else {
                    try {
                        this.addTask(new DeadlineTask(texts[0].strip(), texts[1].strip()));
                    } catch (DateTimeParseException e) {
                        throw new DuckException();
                    }
                }

            } else if (c1 == 'E') {
                String[] texts = next.substring(2).concat(" ").split("/");
                if (texts.length != 3) {
                    throw new DuckException();
                } else {
                    try {
                        this.addTask(new EventTask(texts[0].strip(), texts[1].strip(), texts[2].strip()));
                    } catch (DateTimeParseException e) {
                        throw new DuckException();
                    }
                }

            } else {
                throw new DuckException();
            }

            if (isMarked) {
                this.markTaskAt(this.getLength() - 1);
            }
        }

        sc.close();
    }

    public void writeToDefaultFile() throws IOException {
        ArrayList<String> items = new ArrayList<>(this.getLength());
        for (int i = 0; i < this.getLength(); i++) {
            items.add(this.getTask(i).toCompactString());
        }

        Files.write(Paths.get("./data/duck.txt"), items);
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "***EMPTY LIST***";
        }

        int len = this.list.size();
        String str = "1." + list.get(0);
        for (int i = 1; i < len; i++) {
            str += "\n" + (i + 1) + "." + list.get(i);
        }
        return str;
    }
}
