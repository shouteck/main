package seedu.address.logic.commands;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GENDER;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_HEIGHT;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DIFFICULTY;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_WEIGHT;
//import static seedu.address.commons.core.Messages.MESSAGE_VALID_GENDER;
//import static seedu.address.commons.core.Messages.MESSAGE_VALID_HEIGHT;
//import static seedu.address.commons.core.Messages.MESSAGE_VALID_DIFFICULTY;
//import static seedu.address.commons.core.Messages.MESSAGE_VALID_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_WEIGHT;
/*import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CALORIES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DIFFICULTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;*/
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEIGHT;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFICULTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertModifyCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.parser.ModifyCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ModifyCommandTest {
    public static final String MESSAGE_MODIFY_USERPROFILE_SUCCESS = "User profile has been modified!";

    private static String currentGender;
    private static String currentUsername;
    private static String currentHeight;
    private static String currentWeight;
    private static String currentDifficulty;
    private static String currentCalories;
    private static String currentDuration;

    private String fileName;
    private Document doc;
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() throws IOException {
        String workingDir = System.getProperty("user.dir");
        fileName = workingDir + "/ProfileWindow.html";
        doc = Jsoup.parse(new File(fileName), "UTF-8");

        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divDifficulty = doc.getElementById("difficulty");
        Element divCalories = doc.getElementById("calories");
        Element divDuration = doc.getElementById("duration");

        currentGender = divGender.ownText();
        currentUsername = divUsername.ownText();
        currentHeight = divHeight.ownText();
        currentWeight = divWeight.ownText();
        currentDifficulty = divDifficulty.ownText();
        currentCalories = divCalories.ownText();
        currentDuration = divDuration.ownText();
    }

    @Test
    public void execute_singleField_success() throws IOException, ParseException {
        ArrayList<String> expectedAttributes = new ArrayList<String>();
        ArrayList<String> actualAttributes = new ArrayList<String>();

        String workingDir = System.getProperty("user.dir");
        fileName = workingDir + "/ProfileWindow.html";
        doc = Jsoup.parse(new File(fileName), "UTF-8");

        //execute the command
        Element divGender = doc.getElementById("gender");
        divGender.text(VALID_GENDER);
        String expectedSuccessMessage = MESSAGE_MODIFY_USERPROFILE_SUCCESS;

        expectedAttributes.add(VALID_GENDER);
        actualAttributes.add(divGender.ownText());
        //valid gender
        String commandGender = " " + PREFIX_GENDER + VALID_GENDER;
        assertModifyCommandSuccess(new ModifyCommandParser().parse(commandGender), actualAttributes, model,
                commandHistory, expectedSuccessMessage, expectedAttributes);
    }

    /*@Test
    public void singleField_failure() throws ParseException {

        String expectedFailureMessage = new ParseException(String.format(MESSAGE_INVALID_HEIGHT, MESSAGE_VALID_HEIGHT);

        //valid gender
        String commandGender = " " + PREFIX_HEIGHT + INVALID_HEIGHT;
        assertCommandFailure(new ModifyCommandParser().parse(commandGender), model,
                commandHistory, expectedFailureMessage);
    }*/

    @Test
    public void execute_allField_success() throws IOException, ParseException {
        ArrayList<String> expectedAttributes = new ArrayList<String>();
        ArrayList<String> actualAttributes = new ArrayList<String>();

        String workingDir = System.getProperty("user.dir");
        fileName = workingDir + "/ProfileWindow.html";
        doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divDifficulty = doc.getElementById("difficulty");
        Element divCalories = doc.getElementById("calories");
        Element divDuration = doc.getElementById("duration");
        divGender.text(VALID_GENDER);
        divHeight.text(VALID_HEIGHT);
        divWeight.text(VALID_WEIGHT);
        divUsername.text(VALID_USERNAME);
        divDifficulty.text(VALID_DIFFICULTY);
        divCalories.text(VALID_CALORIES);
        divDuration.text(VALID_DURATION);

        //expected attritubes
        expectedAttributes.add(VALID_CALORIES);
        expectedAttributes.add(VALID_DIFFICULTY);
        expectedAttributes.add(VALID_DURATION);
        expectedAttributes.add(VALID_GENDER);
        expectedAttributes.add(VALID_HEIGHT);
        expectedAttributes.add(VALID_USERNAME);
        expectedAttributes.add(VALID_WEIGHT);

        //actual attributes
        actualAttributes.add(divCalories.ownText());
        actualAttributes.add(divDifficulty.ownText());
        actualAttributes.add(divDuration.ownText());
        actualAttributes.add(divGender.ownText());
        actualAttributes.add(divHeight.ownText());
        actualAttributes.add(divUsername.ownText());
        actualAttributes.add(divWeight.ownText());


        String command = " " + PREFIX_GENDER + VALID_GENDER + " " + PREFIX_CALORIES + VALID_CALORIES + " "
                + PREFIX_DIFFICULTY + VALID_DIFFICULTY + " " + PREFIX_DURATION + VALID_DURATION + " "
                + PREFIX_HEIGHT + VALID_HEIGHT + " " + PREFIX_WEIGHT + VALID_WEIGHT + " " + PREFIX_USERNAME
                + VALID_USERNAME;

        String expectedSuccessMessage = MESSAGE_MODIFY_USERPROFILE_SUCCESS;
        assertModifyCommandSuccess(new ModifyCommandParser().parse(command), actualAttributes, model, commandHistory,
                expectedSuccessMessage, expectedAttributes);
    }

    @Test
    public void execute_invalidFieldEntered_failure() {
        String expectedWeightMessage = String.format(MESSAGE_INVALID_WEIGHT, MESSAGE_VALID_WEIGHT);

    }

    @After
    public void revert() throws IOException {
        String workingDir = System.getProperty("user.dir");
        fileName = workingDir + "/ProfileWindow.html";
        doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divDifficulty = doc.getElementById("difficulty");
        Element divCalories = doc.getElementById("calories");
        Element divDuration = doc.getElementById("duration");

        divGender.text(currentGender);
        divHeight.text(currentHeight);
        divUsername.text(currentUsername);
        divDifficulty.text(currentDifficulty);
        divWeight.text(currentWeight);
        divCalories.text(currentCalories);
        divDuration.text(currentDuration);
    }
}
