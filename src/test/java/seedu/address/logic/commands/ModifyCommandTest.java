package seedu.address.logic.commands;

/*import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEIGHT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREFERRED_DIFFICULTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_DIFFICULTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;*/

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;



public class ModifyCommandTest {
    /*public static final String USERPROFILE_FILE_PATH = "/docs/ProfileWindow.html";
    public static final String MESSAGE_MODIFY_USERPROFILE_SUCCESS = "User profile has been modified! Please rerun the"
            + " MainApp to see the changes.";

    private static String currentGender;
    private static String currentUsername;
    private static String currentHeight;
    private static String currentWeight;
    private static String currentPreferredDifficulty;

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();/*

    /*@Before
    public void setUp() throws IOException {
        String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divPreferredDifficulty = doc.getElementById("preferred_difficulty");

        currentGender = divGender.ownText();
        currentUsername = divUsername.ownText();
        currentHeight = divHeight.ownText();
        currentWeight = divWeight.ownText();
        currentPreferredDifficulty = divPreferredDifficulty.ownText();

        //modify the content of profile window
        divGender.text(INVALID_GENDER);
        divHeight.text(INVALID_HEIGHT);
        divUsername.text(INVALID_USERNAME);
        divWeight.text(INVALID_WEIGHT);
        divPreferredDifficulty.text(INVALID_PREFERRED_DIFFICULTY);
    }*/

    //@Test
    public void execute_singleField_success() {
        /*String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        divGender.text(VALID_GENDER);
        String expectedMessage = String.format(MESSAGE_MODIFY_USERPROFILE_SUCCESS);*/

        // same gender -> returns true
        //assertTrue(divGender.ownText().equals(VALID_GENDER));

        // null gender -> returns false
        //assertFalse(divGender.ownText().equals(null));

        // different gender -> returns false
        //assertFalse(divGender.ownText().equals(INVALID_GENDER));

        /*assertCommandSuccess(new ModifyCommand(VALID_GENDER, INVALID_USERNAME, INVALID_HEIGHT, INVALID_WEIGHT,
                INVALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedMessage, expectedModel);*/
    }

    //@Test
    public void execute_allField_success() {
        /*String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString();
        String Name = "C:/Users/SJ/IdeaProjects/addressbook-level4/docs/ProfileWindow.html";
        //System.out.println(fileName);
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divPreferredDifficulty = doc.getElementById("preferred_difficulty");
        divGender.text(VALID_GENDER);
        divHeight.text(VALID_HEIGHT);
        divWeight.text(VALID_WEIGHT);
        divUsername.text(VALID_USERNAME);
        divPreferredDifficulty.text(VALID_PREFERRED_DIFFICULTY);

        // same gender -> returns true
        assertTrue(divGender.ownText().equals(VALID_GENDER));*/

        // same height -> returns true
        /*assertTrue(divHeight.ownText().equals(VALID_HEIGHT));

        // same weight -> returns true
        assertTrue(divWeight.ownText().equals(VALID_WEIGHT));

        // same username -> returns true
        assertTrue(divUsername.ownText().equals(VALID_USERNAME));

        // same preferred difficulty -> returns true
        assertTrue(divPreferredDifficulty.ownText().equals(VALID_PREFERRED_DIFFICULTY));

        // different gender -> returns false
        assertFalse(divGender.ownText().equals(INVALID_GENDER));

        // different height -> returns false
        assertFalse(divHeight.ownText().equals(INVALID_HEIGHT));

        // different weight -> returns false
        assertFalse(divWeight.ownText().equals(INVALID_WEIGHT));

        // different username -> returns false
        assertFalse(divUsername.ownText().equals(INVALID_USERNAME));

        // different preferred difficulty -> returns false
        assertFalse(divPreferredDifficulty.ownText().equals(INVALID_PREFERRED_DIFFICULTY));

        String expectedMessage = String.format(MESSAGE_MODIFY_USERPROFILE_SUCCESS);
        assertCommandSuccess(new ModifyCommand(VALID_GENDER, VALID_USERNAME, VALID_HEIGHT, VALID_WEIGHT,
                VALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedMessage, expectedModel);*/
    }
    /*
    @Test
    public void execute_invalidFieldEntered_failure() {
        String expectedWeightMessage = String.format(MESSAGE_INVALID_WEIGHT, MESSAGE_VALID_WEIGHT);
        assertCommandFailure(new ModifyCommand(VALID_GENDER, VALID_USERNAME, VALID_HEIGHT, INVALID_WEIGHT,
                VALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedWeightMessage);

        String expectedHeightMessage = String.format(MESSAGE_INVALID_HEIGHT, MESSAGE_VALID_HEIGHT);
        assertCommandFailure(new ModifyCommand(VALID_GENDER, VALID_USERNAME, INVALID_HEIGHT, VALID_WEIGHT,
                VALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedHeightMessage);

        String expectedUsernameMessage = String.format(MESSAGE_INVALID_USERNAME, MESSAGE_VALID_USERNAME);
        assertCommandFailure(new ModifyCommand(VALID_GENDER, INVALID_USERNAME, VALID_HEIGHT, VALID_WEIGHT,
                VALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedUsernameMessage);

        String expectedGenderMessage = String.format(MESSAGE_VALID_GENDER, MESSAGE_VALID_GENDER);
        assertCommandFailure(new ModifyCommand(INVALID_GENDER, VALID_USERNAME, VALID_HEIGHT, VALID_WEIGHT,
                VALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedGenderMessage);

        String expectedDifficultyMessage = String.format(MESSAGE_VALID_PREFERRED_DIFFICULTY,
                MESSAGE_VALID_PREFERRED_DIFFICULTY);
        assertCommandFailure(new ModifyCommand(VALID_GENDER, VALID_USERNAME, VALID_HEIGHT, VALID_WEIGHT,
                INVALID_PREFERRED_DIFFICULTY), model, commandHistory, expectedDifficultyMessage);
    }

    @After
    public void revert() throws IOException {
        String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divPreferredDifficulty = doc.getElementById("preferred_difficulty");
        divGender.text(currentGender);
        divHeight.text(currentHeight);
        divUsername.text(currentUsername);
        divPreferredDifficulty.text(currentPreferredDifficulty);
        divWeight.text(currentWeight);
    }*/
}
