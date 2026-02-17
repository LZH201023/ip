# Duck User Guide

Duck is a task management chatbot that helps you keep track of todos, deadlines, and events.  
It provides a simple interface to organize tasks efficiently and stores them automatically for future use.

---

## Getting Started

### Prerequisites
- Java **17** installed (this is the preferred version).

### Running Duck
1. Download the latest `.jar` file.
2. Open a terminal.
3. Navigate to the folder containing the file.
4. Run:
```
java -jar duck.jar
```
5. Enter commands in the input box or terminal to interact with Duck.

---

## Using Duck

Duck supports the following commands (note that all dates should be in YYYY-MM-DD format):

### Add a todo
Adds a simple task.
```
todo DESCRIPTION
```
---

### Add a deadline
Adds a task with a deadline. 
```
deadline DESCRIPTION /by DATE
```
---

### Add an event
Adds an event with a start and end time.
```
event DESCRIPTION /from START /to END
```
---

### List all tasks
Displays all stored tasks.
```
list
```
---

### Mark a task as done
Marks a task as completed.
```
mark TASK_NUMBER
```
---

### Unmark a task
Marks a task as not completed.
```
unmark TASK_NUMBER
```
---

### Delete a task
Removes a task.
```
delete TASK_NUMBER
```
---

### Find tasks
Finds tasks containing a keyword.
```
find KEYWORD
```
---

### Tag a task
Adds a tag to a task.
```
tag TASK_NUMBER DESCRIPTION
```
---

### Untag a task
Removes tag from a task.
```
untag TASK_NUMBER
```
---

### Exit Duck
Closes the application (this command is case-insensitive).
```
bye
```
---

## Additional Information

- Tasks are saved automatically and loaded when Duck starts.
- Command keywords are case-sensitive, unless otherwise specified.
- Task numbers refer to the index shown in the task list (starting from 1).
- Invalid command formats will display an error message.

---