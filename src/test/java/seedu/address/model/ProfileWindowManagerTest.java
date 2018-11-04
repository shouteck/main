package seedu.address.model;

import static java.util.Objects.requireNonNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;

import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ProfileWindowManager;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;

import org.junit.Test;

import java.io.IOException;

public class ProfileWindowManagerTest {
    
    @Test
    public void isValidGenderTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        String validGender = "male";
        String invalidGender = "males";

        assertTrue(profileWindowManager.isValidGender(validGender));
        assertFalse(profileWindowManager.isValidGender(invalidGender));
    }

    @Test
    public void isValidWeightTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        String validWeight = "66.2";
        String invalidWeight = "94.88";
        String invalidWeight2 = "94.8 kg";
        
        assertTrue(profileWindowManager.isValidWeight(validWeight));
        //Weight should be in the format xx.xx where x is any integer.
        assertFalse(profileWindowManager.isValidWeight(invalidWeight));
        assertFalse(profileWindowManager.isValidWeight(invalidWeight2));
    }

    @Test
    public void isValidHeightTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        String validHeight = "1.77";
        String invalidHeight = "1.734";
        String invalidHeight2 = "177";

        assertTrue(profileWindowManager.isValidHeight(validHeight));
        //Height should be in the format x.xx where x is any integer.
        assertFalse(profileWindowManager.isValidHeight(invalidHeight));
        assertFalse(profileWindowManager.isValidHeight(invalidHeight2));
    }

    @Test
    public void isValidUsernameTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        String validUsername = "John Doe";
        String invalidUsername = "John@Doe";

        assertTrue(profileWindowManager.isValidUsername(validUsername));
        //Name should only consists of alphanumeric characters
        assertFalse(profileWindowManager.isValidUsername(invalidUsername));
    }

    @Test
    public void convertStringToIntegerTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        String validStringInteger = "123";

        assertEquals(profileWindowManager.convertStringIntoInt(validStringInteger),123);
    }

    @Test
    public void isHigherCaloriesTest() throws IOException {
        ProfileWindowManager profileWindowManager;
        profileWindowManager = ProfileWindowManager.getInstance();
        int lowerCalories = 150;
        int higherCalories = 160;
        int sameCalories = 150;
        assertTrue(profileWindowManager.isHigherCalories(higherCalories,lowerCalories));

        //Should return false since lowerCalories is not higher than higherCalories
        assertFalse(profileWindowManager.isHigherCalories(lowerCalories,higherCalories));
        ////Should return false since lowerCalories is not higher than sameCalories
        assertFalse(profileWindowManager.isHigherCalories(lowerCalories,sameCalories));
    }

}
