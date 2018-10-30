package seedu.address.logic.commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER;

public class testTest {
    public static final String USERPROFILE_FILE_PATH = "/htmlFiles/ProfileWindow.html";
    public static final String MESSAGE_MODIFY_USERPROFILE_SUCCESS = "User profile has been modified! Please rerun the"
            + " MainApp to see the changes.";

    private static String currentGender;
    private static String currentUsername;
    private static String currentHeight;
    private static String currentWeight;
    private static String currentPreferredDifficulty;

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void execute_singleField_success() throws IOException {
        String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        /*Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        divGender.text(VALID_GENDER);
        String expectedMessage = String.format(MESSAGE_MODIFY_USERPROFILE_SUCCESS);

        //same gender -> returns true
        assertTrue(divGender.ownText().equals(VALID_GENDER));

        //null gender -> returns false
        assertFalse(divGender.ownText().equals(null));

        // different gender -> returns false
        assertFalse(divGender.ownText().equals(INVALID_GENDER));*/

        /*assertCommandSuccess(new ModifyCommand(VALID_GENDER, INVALID_USERNAME, INVALID_HEIGHT, INVALID_WEIGHT,
                INVALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedMessage, expectedModel);*/
    }

}
