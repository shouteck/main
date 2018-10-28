package seedu.address.model;

import static seedu.address.ui.ProfileWindow.USERPROFILE_FILE_PATH;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;

/**
 * Acts as a Facade for any methods to do with ProfileWindow
 * Singleton Pattern
 */
public class ProfileWindowManager {

    private static final String GENDER_VALIDATION_REGEX = "(male)|(female)";
    private static final String HEIGHT_VALIDATION_REGEX = "\\d{1}\\.\\d{2}";
    private static final String WEIGHT_VALIDATION_REGEX = "\\d{2,3}\\.\\d{1}";
    private static final String USERNAME_VALIDATION_REGEX = "[\\p{Alnum}|'][\\p{Alnum} |' ]*";

    private static ProfileWindowManager singleInstance = null;

    private Element gender;
    private Element username;
    private Element height;
    private Element weight;
    private Element calories;
    private Element difficulty;
    private Element duration;
    private Element bmi;

    private Document doc;
    private String fileName;

    private ProfileWindowManager() throws IOException {
        fileName = getClass().getResource(USERPROFILE_FILE_PATH).toString().substring(6);
        doc = Jsoup.parse(new File(fileName), "UTF-8");

        this.gender = doc.getElementById("gender");
        this.username = doc.getElementById("username");
        this.height = doc.getElementById("height");
        this.weight = doc.getElementById("weight");
        this.calories = doc.getElementById("calories");
        this.difficulty = doc.getElementById("difficulty");
        this.duration = doc.getElementById("duration");
        this.bmi = doc.getElementById("bmi");
    }

    public static ProfileWindowManager getInstance() throws IOException {
        if (singleInstance == null) {
            synchronized (ProfileWindowManager.class) {
                singleInstance = new ProfileWindowManager();
            }
        }
        return singleInstance;
    }

    public void writeToFile() throws IOException {
        File temp = File.createTempFile("tempfile", ".html");
        FileUtils.writeStringToFile(temp, doc.outerHtml(), "UTF-8");
        File newFile = new File(fileName);
        org.apache.commons.io.FileUtils.copyFile(temp, newFile);
        org.apache.commons.io.FileUtils.forceDelete(temp);
    }

    public Element getGender() {
        return gender;
    }

    public Element getUsername() {
        return username;
    }

    public Element getHeight() {
        return height;
    }

    public Element getWeight() {
        return weight;
    }

    public Element getCalories() {
        return calories;
    }

    public Element getDifficulty() {
        return difficulty;
    }

    public Element getDuration() {
        return duration;
    }

    public Element getBmi() {
        return bmi;
    }

    public void setGender(String gender) {
        this.gender.text(gender);
    }

    public void setUsername(String username) {
        this.username.text(username);
    }

    public void setHeight(String height) {
        this.height.text(height);
    }

    public void setWeight(String weight) {
        this.weight.text(weight);
    }

    public void setCalories(String calories) {
        this.calories.text(calories);
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.text(difficulty);
    }

    public void setDuration(String duration) {
        this.duration.text(duration);
    }

    public void setBmi(String bmi) {
        this.bmi.text(bmi);
    }

    private String extractHeight(String height) {
        height = height.replaceFirst("Height : ", "");
        height = height.replace("m", "");
        return height;
    }

    private String extractWeight(String weight) {
        weight = weight.replaceFirst("kg", "");
        weight = weight.replaceFirst("Weight : ", "");
        return weight;
    }

    public Optional<Calories> extractCalories() {
        return Optional.of(new Calories(calories.ownText().substring(10)));
    }

    public Optional<Difficulty> extractDifficulty() {
        return Optional.of(new Difficulty(difficulty.ownText().substring(12)));
    }

    public Optional<Duration> extractDuration() {
        return Optional.of(new Duration(duration.ownText().substring(10)));
    }

    public double calculateBmi(String height, String weight) {
        double h = Double.parseDouble(extractHeight(height));
        double w = Double.parseDouble(extractWeight(weight));
        return w / (h * h);
    }

    /**
     * Returns true if the given string is a valid gender
     */
    public boolean isValidGender(String gender) {
        return gender.toLowerCase().matches(GENDER_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid height
     */
    public boolean isValidHeight(String height) {
        return height.matches(HEIGHT_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid weight
     */
    public boolean isValidWeight(String weight) {
        return weight.matches(WEIGHT_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid username
     */
    public boolean isValidUsername(String username) {
        return username.matches(USERNAME_VALIDATION_REGEX);
    }

}
