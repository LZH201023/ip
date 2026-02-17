package duck.ui;

import duck.Duck;
import duck.command.CommandType;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    private static final String USER_IMAGE_PATH = "/images/DaUser.png";
    private static final String DUKE_IMAGE_PATH = "/images/DaDuck.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duck duck;

    /*
     * AI-assisted improvement:
     * This code was improved using ChatGPT (GPT-5.2) to handle potential null values
     * from getResourceAsStream(). The tool suggested adding explicit null checks and
     * meaningful error messages to prevent a NullPointerException if the image
     * resource cannot be found. I reviewed and adapted the suggestion to preserve
     * the original structure of the image initialization.
     */
    private final Image userImage = new Image(
            java.util.Objects.requireNonNull(
                    this.getClass().getResourceAsStream(USER_IMAGE_PATH),
                    "Image resource not found: /images/DaUser.png"
            )
    );

    private final Image dukeImage = new Image(
            java.util.Objects.requireNonNull(
                    this.getClass().getResourceAsStream(DUKE_IMAGE_PATH),
                    "Image resource not found: /images/DaDuck.png"
            )
    );

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duck instance.
     */
    public void setDuck(Duck d) {
        duck = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duck's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        CommandType commandType = duck.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, commandType)
        );

        if (commandType.equals(CommandType.EXIT_COMMAND)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }

}
