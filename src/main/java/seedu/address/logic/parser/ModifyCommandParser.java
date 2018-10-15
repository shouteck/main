package seedu.address.logic.parser;

import java.io.File;
import java.io.IOException;
//import java.awt.Desktop;
import java.text.DecimalFormat;
import java.util.stream.Stream;


//import javafx.stage.Stage;
//import javafx.scene.*;
//import javafx.fxml.FXMLLoader;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GENDER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_HEIGHT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREFERRED_DIFFICULTY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_WEIGHT;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_GENDER;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_HEIGHT;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_PREFERRED_DIFFICULTY;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_VALID_WEIGHT;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

public class ModifyCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ModifyCommand}
     * and returns a {@code ModifyCommand} object for execution.
     */
    public static final String USERPROFILE_FILE_PATH = "/docs/ProfileWindow.html";
    public static final String GENDER_VALIDATION_REGEX = "(male)|(female)";
    public static final String HEIGHT_VALIDATION_REGEX = "\\d{1}\\.\\d{2}";
    public static final String WEIGHT_VALIDATION_REGEX = "\\d{2,3}\\.\\d{1}";
    public static final String USERNAME_VALIDATION_REGEX = "[\\p{Alnum}|'][\\p{Alnum} |' ]*";
    public static final String DIFFICULTY_VALIDATION_REGEX = "(beginner)|(intermediate)|(advanced)";

    //Stage root;
    //private static final String FXML = "ProfileWindow.fxml";
    //private final FXMLLoader fxmlLoader = new FXMLLoader();

    public ModifyCommand parse(String args) throws IOException, ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_PREFERRED_DIFFICULTY, PREFIX_GENDER);

        if (!isPrefixPresent(argMultimap, PREFIX_USERNAME, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_PREFERRED_DIFFICULTY,
                PREFIX_GENDER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModifyCommand.MESSAGE_USAGE));
        }

        String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element divGender = doc.getElementById("gender");
        Element divUsername = doc.getElementById("username");
        Element divHeight = doc.getElementById("height");
        Element divWeight = doc.getElementById("weight");
        Element divPreferredDifficulty = doc.getElementById("preferred_difficulty");
        Element divBmi = doc.getElementById("bmi");
        String newGender = divGender.ownText();
        String newHeight = divHeight.ownText();
        String newWeight = divWeight.ownText();
        String newPreferredDifficulty = divPreferredDifficulty.ownText();
        String newUsername = divUsername.ownText();


        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            String gender = argMultimap.getValue(PREFIX_GENDER).get();
            if (!isValidGender(gender)) {
                throw new ParseException(String.format(MESSAGE_INVALID_GENDER, MESSAGE_VALID_GENDER));
            }
            divGender.text("Gender : " + gender);
            newGender = gender;
        }
        if (argMultimap.getValue(PREFIX_USERNAME).isPresent()) {
            String username = argMultimap.getValue(PREFIX_USERNAME).get();
            if (!isValidUsername(username)) {
                throw new ParseException(String.format(MESSAGE_INVALID_USERNAME, MESSAGE_VALID_USERNAME));
            }
            divUsername.text(username);
            newUsername = username;
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            String height = argMultimap.getValue(PREFIX_HEIGHT).get();
            if (!isValidHeight(height)) {
                throw new ParseException(String.format(MESSAGE_INVALID_HEIGHT, MESSAGE_VALID_HEIGHT));
            }
            divHeight.text("Height : " + height + "m");
            newHeight = height;
        }
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            String weight = argMultimap.getValue(PREFIX_WEIGHT).get();
            if (!isValidWeight(weight)) {
                throw new ParseException(String.format(MESSAGE_INVALID_WEIGHT, MESSAGE_VALID_WEIGHT));
            }
            divWeight.text("Weight : " + weight + "kg");
            newWeight = weight;
        }
        if (argMultimap.getValue(PREFIX_PREFERRED_DIFFICULTY).isPresent()) {
            String preferredDifficulty = argMultimap.getValue(PREFIX_PREFERRED_DIFFICULTY).get();
            if (!isValidPreferredDifficulty(preferredDifficulty)) {
                throw new ParseException(String.format(MESSAGE_INVALID_PREFERRED_DIFFICULTY,
                        MESSAGE_VALID_PREFERRED_DIFFICULTY));
            }
            divPreferredDifficulty.text("User's preferred difficulty: " + preferredDifficulty);
            newPreferredDifficulty = preferredDifficulty;
        }
        newHeight = newHeight.replaceFirst("Height : ", "");
        newHeight = newHeight.replace("m", "");
        newWeight = newWeight.replaceFirst("kg", "");
        newWeight = newWeight.replaceFirst("Weight : ", "");
        double h = Double.parseDouble(newHeight);
        double w = Double.parseDouble(newWeight);
        double calculateBmi = w / (h * h);

        DecimalFormat df = new DecimalFormat("#.#");
        divBmi.text("BMI : " + df.format(calculateBmi));
        File temp = File.createTempFile("tempfile", ".html");
        FileUtils.writeStringToFile(temp, doc.outerHtml(), "UTF-8");
        File newFile = new File(fileName);
        org.apache.commons.io.FileUtils.copyFile(temp, newFile);
        org.apache.commons.io.FileUtils.forceDelete(temp);

        //fxmlLoader.getRoot();
        //Stage root = FXMLLoader.load(getClass().getResource(FXML));

        //seedu.address.ui.UiPart.loadFxmlFile(seedu.address.ui.UiPart.getFxmlFileUrl(FXML), root);
        //new ProfileWindow(root);


        //Desktop desktop = Desktop.getDesktop();
        //desktop.open(newFile);

        return new ModifyCommand(newGender, newUsername, newHeight, newWeight, newPreferredDifficulty);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isValidGender(String gender) {
        return gender.toLowerCase().matches(GENDER_VALIDATION_REGEX);
    }

    private static boolean isValidHeight(String height) {
        return height.matches(HEIGHT_VALIDATION_REGEX);
    }

    private static boolean isValidWeight(String weight) {
        return weight.matches(WEIGHT_VALIDATION_REGEX);
    }

    private static boolean isValidUsername(String username) {
        return username.matches(USERNAME_VALIDATION_REGEX);
    }

    private static boolean isValidPreferredDifficulty(String preferredDifficulty) {
        return preferredDifficulty.toLowerCase().matches(DIFFICULTY_VALIDATION_REGEX);
    }
}
