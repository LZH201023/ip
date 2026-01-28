package duck;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

import duck.task.DeadlineTask;
import duck.task.EventTask;
import duck.task.TaskList;
import duck.task.TodoTask;

/**
 * Manages loading and saving task data to a local file.
 * This class creates the data directory if it does not exist and initializes the storage file.
 */
public class Storage {

    private File file;
    private boolean hasMemoryCheckPassed;
    private boolean isEmptyFile;

    /**
     * Constructs a {@code Storage} instance using the specified file path.
     * This constructor initializes the file reference and checks storage availability.
     *
     * @param filePath The file path used to store and load task data.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.hasMemoryCheckPassed = true;
        checkMemory();
    }

    /**
     * Checks whether the storage directory and file can be created or accessed.
     * This method creates the {@code data} directory if it does not exist.
     * This method also creates the storage file if it does not already exist.
     */
    private void checkMemory() {
        try {
            Path dir = Paths.get("data");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            this.isEmptyFile = file.createNewFile();
        } catch (IOException e) {
            this.hasMemoryCheckPassed = false;
        }
    }

    /**
     * Returns whether storage initialization was successful.
     *
     * @return {@code true} if storage initialization succeeded. Otherwise, {@code false}.
     */
    public boolean getStatus() {
        return hasMemoryCheckPassed;
    }

    /**
     * Loads tasks from the storage file and returns them as a {@link TaskList}.
     * This method returns an empty task list if the storage file was newly created.
     * This method throws {@link DuckException} if the file contents are malformed or cannot be read.
     *
     * @return A {@link TaskList} containing tasks loaded from storage.
     * @throws DuckException If the storage data is invalid or if an I/O error occurs.
     */
    public TaskList load() throws DuckException {
        if (this.isEmptyFile) {
            return new TaskList();
        }

        TaskList list = new TaskList();
        try {
            Scanner sc = new Scanner(file);
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
                        list.addTask(new TodoTask(description));
                    }

                } else if (c1 == 'D') {
                    String[] texts = next.substring(2).concat(" ").split("/");
                    if (texts.length != 2) {
                        throw new DuckException();
                    } else {
                        try {
                            list.addTask(new DeadlineTask(texts[0].strip(), texts[1].strip()));
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
                            list.addTask(new EventTask(texts[0].strip(), texts[1].strip(), texts[2].strip()));
                        } catch (DateTimeParseException e) {
                            throw new DuckException();
                        }
                    }
                } else {
                    throw new DuckException();
                }

                if (isMarked) {
                    list.markTaskAt(list.getLength() - 1);
                }
            }

            sc.close();
            return list;
        } catch (IOException e) {
            throw new DuckException();
        }
    }

    /**
     * Writes the given task list to the storage file.
     * Each task is written in a compact format suitable for later loading.
     *
     * @param tasks The task list to be written to storage.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(TaskList tasks) throws IOException {
        ArrayList<String> items = new ArrayList<>(tasks.getLength());
        for (int i = 0; i < tasks.getLength(); i++) {
            items.add(tasks.getTask(i).toCompactString());
        }

        Files.write(Paths.get("./data/duck.txt"), items);
    }

}
