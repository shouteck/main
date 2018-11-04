package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a profile page
 */
public class ProfileWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ProfileWindow.class);
    private static final String FXML = "ProfileWindow.fxml";

    @FXML
    private WebView browser;

    /**
     * Creates a new ProfileWindow.
     *
     * @param root Stage to use as the root of the ProfileWindow.
     */
    public ProfileWindow(Stage root) {
        super(FXML, root);

        String workingDir = System.getProperty("user.dir");
        String userProfileUrl = workingDir + "\\ProfileWindow.html";

        browser.getEngine().load(userProfileUrl);
    }

    /**
     * Creates a new ProfileWindow.
     */
    public ProfileWindow() {
        this(new Stage());
    }

    /**
     * Shows the profile window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() throws IOException {
        logger.fine("Showing profile page about the application.");
        String workingDir = System.getProperty("user.dir");
        String userProfileUrl = workingDir + "\\ProfileWindow.html";
        browser.getEngine().load(userProfileUrl);
        File htmlFile = new File(userProfileUrl);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }

}
