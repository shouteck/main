package seedu.address.logic.parser;



import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import java.io.*;
import org.apache.commons.io.*;
import java.text.DecimalFormat;



import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

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

    public ModifyCommand parse(String args) throws IOException,ParseException{
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_PREFERRED_DIFFICULTY, PREFIX_GENDER);

        if (!isPrefixPresent(argMultimap, PREFIX_USERNAME, PREFIX_HEIGHT, PREFIX_WEIGHT,PREFIX_PREFERRED_DIFFICULTY,
                PREFIX_GENDER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModifyCommand.MESSAGE_USAGE));
        }


        String fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);

        Document doc = Jsoup.parse(new File(fileName), "UTF-8");
        Element div_gender = doc.getElementById("gender");
        Element div_username = doc.getElementById("username");
        Element div_height = doc.getElementById("height");
        Element div_weight = doc.getElementById("weight");
        Element div_preferred_difficulty = doc.getElementById("preferred_difficulty");
        Element div_bmi = doc.getElementById("bmi");
        String newGender = div_gender.ownText();
        String newHeight = div_height.ownText();
        String newWeight = div_weight.ownText();
        String newPreferredDifficulty = div_preferred_difficulty.ownText();
        String newUsername = div_username.ownText();


        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            String Gender = argMultimap.getValue(PREFIX_GENDER).get();
            if (!isValidGender(Gender)) {
                throw new ParseException(String.format(MESSAGE_INVALID_GENDER, MESSAGE_VALID_GENDER));
            }
            div_gender.text("Gender : " + Gender);
            newGender = Gender;
        }
        if (argMultimap.getValue(PREFIX_USERNAME).isPresent()) {
            String Username = argMultimap.getValue(PREFIX_USERNAME).get();
            if (!isValidUsername(Username)) {
                throw new ParseException(String.format(MESSAGE_INVALID_USERNAME, MESSAGE_VALID_USERNAME));
            }
            div_username.text(Username);
            newUsername = Username;
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            String Height = argMultimap.getValue(PREFIX_HEIGHT).get();
            if (!isValidHeight(Height)) {
                throw new ParseException(String.format(MESSAGE_INVALID_HEIGHT, MESSAGE_VALID_HEIGHT));
            }
            div_height.text("Height : " + Height + "m");
            newHeight = Height;
        }
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            String Weight = argMultimap.getValue(PREFIX_WEIGHT).get();
            if (!isValidWeight(Weight)) {
                throw new ParseException(String.format(MESSAGE_INVALID_WEIGHT, MESSAGE_VALID_WEIGHT));
            }
            div_weight.text("Weight : " + Weight + "kg");
            newWeight = Weight;
        }
        if (argMultimap.getValue(PREFIX_PREFERRED_DIFFICULTY).isPresent()) {
            String Preferred_Difficulty = argMultimap.getValue(PREFIX_PREFERRED_DIFFICULTY).get();
            if (!isValidPreferredDifficulty(Preferred_Difficulty)) {
                throw new ParseException(String.format(MESSAGE_INVALID_PREFERRED_DIFFICULTY,
                        MESSAGE_VALID_PREFERRED_DIFFICULTY));
            }
            div_preferred_difficulty.text("User's preferred difficulty: " + Preferred_Difficulty);
            newPreferredDifficulty = Preferred_Difficulty;
        }
        newHeight = newHeight.replaceFirst("Height : ","");
        newHeight = newHeight.replace("m","");
        newWeight = newWeight.replaceFirst("kg","");
        newWeight = newWeight.replaceFirst("Weight : ","");
        double h = Double.parseDouble(newHeight);
        double w = Double.parseDouble(newWeight);
        double CalculateBMI = w / (h * h);

        DecimalFormat df = new DecimalFormat("#.#");
        div_bmi.text("BMI : " + df.format(CalculateBMI));
        File temp = File.createTempFile("tempfile", ".html");
        FileUtils.writeStringToFile(temp, doc.outerHtml(), "UTF-8");
        File newFile = new File(fileName);
        org.apache.commons.io.FileUtils.copyFile(temp, newFile);
        org.apache.commons.io.FileUtils.forceDelete(temp);

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

    private static boolean isValidPreferredDifficulty(String preferred_difficulty) {
        return preferred_difficulty.toLowerCase().matches(DIFFICULTY_VALIDATION_REGEX);
    }
}
